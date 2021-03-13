package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TJLogsEditFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private ThoughtJournalObject thoughtJournalData;
    private RatingBar emotionRatingLog;
    private TextView dateEditLog;
    private EditText placeEditLog, peopleEditLog, situationEditLog, behaviorEditLog, emotionDetailsEditLog, thoughtsEditLog;
    private RadioGroup timeLogRadioGroup , peopleLogRadioGroup, emotionLogRadioGroup;
    private ChipGroup thoughtChipGroup;
    private Button addThoughtButton;
    List<String> thoughtList;

    public TJLogsEditFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logs_fragment_tj_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        getViewModelData();
        setRadioGroups(view);
        setRatingBar();
        setButtons();
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                setViewModelText();
                updateFirestoreDocument();
                controller.popBackStack(R.id.tjLogFragment, true);
                controller.navigate(R.id.tjLogFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        dateEditLog = view.findViewById(R.id.tj_edit_log_date);
        placeEditLog = view.findViewById(R.id.place_edit_log);
        timeLogRadioGroup = view.findViewById(R.id.time_log_radiogroup);
        peopleLogRadioGroup = view.findViewById(R.id.people_log_radiogroup);
        peopleEditLog = view.findViewById(R.id.people_edit_log);
        situationEditLog = view.findViewById(R.id.situation_edit_log);
        behaviorEditLog = view.findViewById(R.id.behavior_edit_log);
        emotionLogRadioGroup = view.findViewById(R.id.emotion_log_radiogroup);
        emotionRatingLog = view.findViewById(R.id.emotion_rating_log);
        emotionDetailsEditLog = view.findViewById(R.id.emotion_details_edit_log);
        thoughtsEditLog = view.findViewById(R.id.thought_log_input);
        thoughtChipGroup = view.findViewById(R.id.tj_thoughts_log_chipgroup);
        addThoughtButton = view.findViewById(R.id.add_thought_log_button);

    }

    private void getViewModelData() {
        thoughtJournalData = logsViewModel.getThoughtJournalLog();

        dateEditLog.setText(String.format("Created: %s", thoughtJournalData.getDateCreated()));
        List<EditText> textFields =  new ArrayList<>(Arrays. asList(placeEditLog, peopleEditLog, situationEditLog, behaviorEditLog, emotionDetailsEditLog, thoughtsEditLog));
        List<String> inputs =  new ArrayList<>(Arrays. asList(thoughtJournalData.getLocation(), thoughtJournalData.getPeople(), thoughtJournalData.getSituation(), thoughtJournalData.getBehavior(), thoughtJournalData.getEmotionDetail(), thoughtJournalData.getThoughts()));
        for(int i = 0; i < textFields.size(); i++){
            if (!(textFields.get(i) == peopleEditLog && inputs.get(i).equals("Alone"))){
                textFields.get(i).setText(inputs.get(i));
            }
        }

        emotionRatingLog.setRating(thoughtJournalData.getEmotionRating());

        switch (thoughtJournalData.getTime()){
            case "Morning":
                timeLogRadioGroup.check(R.id.morning_log);
                break;
            case "Noonish":
                timeLogRadioGroup.check(R.id.noonish_log);
                break;
            case "Afternoon":
                timeLogRadioGroup.check(R.id.afternoon_log);
                break;
            case "Evening":
                timeLogRadioGroup.check(R.id.evening_log);
                break;
            case "At night":
                timeLogRadioGroup.check(R.id.night_log);
                break;
            default:
                timeLogRadioGroup.check(R.id.morning_log);
                break;
        }

        if(thoughtJournalData.getPeople().equals("Alone")){
            peopleLogRadioGroup.check(R.id.alone_log);
        }
        else{
            peopleLogRadioGroup.check(R.id.with_othersLog);
            peopleEditLog.setText(thoughtJournalData.getPeople());
        }

        switch (thoughtJournalData.getEmotion()){
            case "Sadness":
                emotionLogRadioGroup.check(R.id.sadness_log);
                break;
            case "Anger":
                emotionLogRadioGroup.check(R.id.anger_log);
                break;
            case "Fear":
                emotionLogRadioGroup.check(R.id.fear_log);
                break;
            case "Happiness":
                emotionLogRadioGroup.check(R.id.happiness_log);
                break;
        }
    }

    private void setViewModelText() {
        thoughtJournalData.setLocation(placeEditLog.getText().toString());
        if(!thoughtJournalData.getPeople().equals("Alone")){
            thoughtJournalData.setPeople(peopleEditLog.getText().toString());
        }

        thoughtJournalData.setSituation(situationEditLog.getText().toString());
        thoughtJournalData.setBehavior(behaviorEditLog.getText().toString());
        thoughtJournalData.setEmotionDetail(emotionDetailsEditLog.getText().toString());
        setThoughtChips();

        if(thoughtJournalData.getPeople().equals("") || thoughtJournalData.getPeople().isEmpty()){
            thoughtJournalData.setPeople("With others");
        }
    }

    private void setThoughtChips() {
        thoughtList = Arrays.asList(thoughtJournalData.getThoughts().split(",", -1));
        for(String thought: thoughtList){
            createThoughtChip(thought);
        }
    }

    private void setRadioGroups(View view) {
        timeLogRadioGroup.setOnCheckedChangeListener((group, checkedId) ->{
            RadioButton timeRadioButton = view.findViewById(checkedId);
            thoughtJournalData.setTime((timeRadioButton.getText()).toString());
        });
        peopleLogRadioGroup.setOnCheckedChangeListener((group, checkedId) ->{
            RadioButton peopleRadioButton = view.findViewById(checkedId);
            thoughtJournalData.setPeople((peopleRadioButton.getText()).toString());
        });
        emotionLogRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton emotionRadioButton =  view.findViewById(checkedId);
            thoughtJournalData.setEmotion((emotionRadioButton.getText()).toString());
        });
    }

    private void setRatingBar() {
        emotionRatingLog.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> thoughtJournalData.setEmotionRating(rating));
    }

    private void setButtons() {
        addThoughtButton.setOnClickListener(v ->{
            String thought = thoughtsEditLog.getText().toString().trim();
            if (!thought.equals("") && !thought.isEmpty()){
                createThoughtChip(thought);
                thoughtList.add(thought);
                thoughtsEditLog.setText("");
            }
        });
    }

    private void createThoughtChip(String thought) {
        Chip chip = new Chip(requireActivity());
        chip.setText(thought);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setTextColor(getResources().getColor(R.color.colorWhite));
        chip.setCloseIconVisible(true);
        chip.setCloseIconTintResource(R.color.colorWhite);
        thoughtChipGroup.addView(chip);
        chip.setOnCloseIconClickListener(view -> {
            thoughtChipGroup.removeView(chip);
            thoughtList.remove(thought);
        });
    }

    private void updateFirestoreDocument() {

        thoughtJournalData.setThoughtList(thoughtList);

        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "time", thoughtJournalData.getTime(),
                "location", thoughtJournalData.getLocation(),
                "people", thoughtJournalData.getPeople(),
                "situation", thoughtJournalData.getSituation(),
                "behavior", thoughtJournalData.getBehavior(),
                "emotion", thoughtJournalData.getEmotion(),
                "emotionRating", thoughtJournalData.getEmotionRating(),
                "emotionDetail", thoughtJournalData.getEmotionDetail(),
                "thoughts", thoughtJournalData.getThoughtList()).addOnFailureListener(e -> Log.e("UPDATING THOUGHTJOURNAL", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATING THOUGHTJOURNAL", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));
    }

}