package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;


public class TjLogsFragment extends Fragment {
    LogsViewModel logsViewModel;
    private ThoughtJournalObject thoughtJournalData;
    private TextView  dateLog, placeLog, timeLog, peopleLog, situationLog, behaviorLog, emotionLog, emotionRatingLog, emotionDetailsLog, thoughtsLog;
    private ImageView editPlaceLog, editTimeLog, editPeopleLog, editSituationLog, editBehaviorLog, editEmotionLog, editThoughtsLog;


    public TjLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
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
        findViews(view);
        getViewModelData();
    }

    private void getViewModelData() {
        thoughtJournalData = logsViewModel.getThoughtJournalLog();
        String dateText = "Created: " + thoughtJournalData.getDateCreated();
        dateLog.setText(dateText);
        placeLog.setText(thoughtJournalData.getLocation());
        timeLog.setText(thoughtJournalData.getTime());
        peopleLog.setText(thoughtJournalData.getPeople());
        situationLog.setText(thoughtJournalData.getSituation());
        behaviorLog.setText(thoughtJournalData.getBehavior());
        emotionLog.setText(thoughtJournalData.getEmotion());
        emotionRatingLog.setText(String.valueOf(thoughtJournalData.getEmotionRating()));
        emotionDetailsLog.setText(thoughtJournalData.getEmotionDetail());

        String thoughts = "";
        String[] thoughtList = thoughtJournalData.getThoughts().split("\\s*,\\s*");
        for(String thought: thoughtList){
            thoughts += "-" + thought + "\n";
        }
        thoughtsLog.setText(thoughts);
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

        editPlaceLog = view.findViewById(R.id.edit_place_icon);
        editTimeLog = view.findViewById(R.id.edit_time_icon);
        editPeopleLog = view.findViewById(R.id.edit_people_icon);
        editSituationLog = view.findViewById(R.id.edit_situation_icon);
        editBehaviorLog = view.findViewById(R.id.edit_behavior_icon);
        editEmotionLog = view.findViewById(R.id.edit_emotion_icon);
        editThoughtsLog = view.findViewById(R.id.edit_thoughts_icon);
    }
}