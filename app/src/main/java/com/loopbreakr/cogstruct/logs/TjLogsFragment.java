package com.loopbreakr.cogstruct.logs;

import android.graphics.Typeface;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TjLogsFragment extends Fragment {
    LogsViewModel logsViewModel;
    private Menu topMenu;
    private ThoughtJournalObject thoughtJournalData;
    private TextView  dateLog, placeLog, timeLog, peopleLog, situationLog, behaviorLog, emotionLog, emotionRatingLog, emotionDetailsLog, thoughtsLog;


    public TjLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.logs_fragment_tj, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        getViewModelData();
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.logsToolbar);
        toolbar.setOnMenuItemClickListener(item -> {
            NavController controller = Navigation.findNavController(requireView());
            switch (item.getItemId()) {
                case R.id.action_editLog :
                    controller.navigate(R.id.action_tjLogFragment_to_TJLogEditFragment);
                    return true;
                case R.id.action_deleteLog:
                    DocumentSnapshot snapshot = logsViewModel.getSnapshot();
                    snapshot.getReference().delete().addOnFailureListener(e ->
                            Log.e("DELETING...", "deleteSnapshot: " + snapshot.getData(), e)).addOnSuccessListener(aVoid ->
                            Log.d("DELETING...", "deleteSnapshot: " + snapshot.getData()));
                    controller.popBackStack(R.id.allLogsFragment, true);
                    controller.navigate(R.id.allLogsFragment);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        });
    }

    private void getViewModelData() {
        thoughtJournalData = logsViewModel.getThoughtJournalLog();

        List<TextView> textFields =  new ArrayList<>(Arrays. asList(dateLog, placeLog, timeLog, peopleLog, situationLog, behaviorLog, emotionLog, emotionRatingLog, emotionDetailsLog));
        List<String> inputs =  new ArrayList<>(Arrays. asList("Created: " + thoughtJournalData.getDateCreated(), thoughtJournalData.getLocation(), thoughtJournalData.getTime(), thoughtJournalData.getPeople(), thoughtJournalData.getSituation(), thoughtJournalData.getBehavior(), thoughtJournalData.getEmotion(), String.valueOf(thoughtJournalData.getEmotionRating()), thoughtJournalData.getEmotionDetail()));

        for(int i = 0; i < textFields.size(); i++){
            if(inputs.get(i).equals("") || inputs.get(i).isEmpty() || inputs.get(i).equals(null)){
                textFields.get(i).setText(R.string.logs_empty_field);
            }
            else{
                textFields.get(i).setText(inputs.get(i));
                textFields.get(i).setTypeface(Typeface.DEFAULT);
            }


        }

        StringBuilder thoughts = new StringBuilder();
        String[] thoughtList = thoughtJournalData.getThoughts().split("\\s*,\\s*");
        for(String thought: thoughtList){
            thoughts.append("-").append(thought).append("\n\n");
        }
        thoughtsLog.setText(thoughts.toString());
        thoughtsLog.setTypeface(Typeface.DEFAULT);
    }

    private void findViews(View view) {
        dateLog = view.findViewById(R.id.tj_log_date);
        placeLog = view.findViewById(R.id.place_log);
        timeLog = view.findViewById(R.id.time_log);
        peopleLog = view.findViewById(R.id.people_log);
        situationLog = view.findViewById(R.id.situation_log);
        behaviorLog = view.findViewById(R.id.behavior_log);
        emotionLog = view.findViewById(R.id.emotion_log);
        emotionRatingLog = view.findViewById(R.id.emotion_rating_log);
        emotionDetailsLog = view.findViewById(R.id.emotion_details_log);
        thoughtsLog = view.findViewById(R.id.thoughts_log);

    }

}