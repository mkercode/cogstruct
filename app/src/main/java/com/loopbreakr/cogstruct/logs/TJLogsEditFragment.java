package com.loopbreakr.cogstruct.logs;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TJLogsEditFragment extends Fragment {
    LogsViewModel logsViewModel;
    private ThoughtJournalObject thoughtJournalData;
    private RatingBar emotionRatingLog;
    TextView dateEditLog;
    EditText placeEditLog, peopleEditLog, situationEditLog, behaviorEditLog, emotionDetailsEditLog, thoughtsEditLog;
    private RadioGroup timeLogRadioGroup , peopleLogRadioGroup, emotionLogRadioGroup;

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
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                //code to submit viewmodel data to firebase
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
        thoughtsEditLog = view.findViewById(R.id.thoughts_edit_log);
    }

    private void getViewModelData() {
        thoughtJournalData = logsViewModel.getThoughtJournalLog();

        dateEditLog.setText(String.format("Created: %s", thoughtJournalData.getDateCreated()));
        List<EditText> textFields =  new ArrayList<>(Arrays. asList(placeEditLog, peopleEditLog, situationEditLog, behaviorEditLog, emotionDetailsEditLog, thoughtsEditLog));
        List<String> inputs =  new ArrayList<>(Arrays. asList(thoughtJournalData.getLocation(), thoughtJournalData.getPeople(), thoughtJournalData.getSituation(), thoughtJournalData.getBehavior(), thoughtJournalData.getEmotionDetail(), thoughtJournalData.getThoughts()));
        for(int i = 0; i < textFields.size(); i++){
            textFields.get(i).setText(inputs.get(i));
        }


    }

}