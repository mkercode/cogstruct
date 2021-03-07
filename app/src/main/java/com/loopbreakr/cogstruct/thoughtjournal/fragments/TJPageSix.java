package com.loopbreakr.cogstruct.thoughtjournal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.activities.TJActivity;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TJPageSix extends Fragment{
    private TJViewModel tjViewModel;
    TextView placeReview, timeReview, peopleReview, situationReview, behaviorReview, mainEmotionReview, emotionIntensityReview, emotionDetailsReview, thoughtsReview;
    List<TextView> requiredFields;
    Button editButton, submitButton;
    String thoughts = "";
    public TJPageSix() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tj_page_six, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViews(view);
        fillTextViews();
        setButtons();
        submitData();
    }



    private void getViews(View view) {
        placeReview = view.findViewById(R.id.place_review);
        timeReview = view.findViewById(R.id.time_review);
        peopleReview = view.findViewById(R.id.people_review);
        situationReview = view.findViewById(R.id.situation_review);
        behaviorReview = view.findViewById(R.id.behavior_review);
        mainEmotionReview = view.findViewById(R.id.main_emotion_review);
        emotionIntensityReview = view.findViewById(R.id.emotion_intensity_review);
        emotionDetailsReview = view.findViewById(R.id.emotion_details_review);
        thoughtsReview = view.findViewById(R.id.thoughts_review);
        editButton = view.findViewById(R.id.tj_edit);
        submitButton = view.findViewById(R.id.tj_submit);
    }

    private void fillTextViews() {
        List<TextView> textFields =  new ArrayList<>(Arrays. asList(placeReview, timeReview, peopleReview, situationReview, behaviorReview, mainEmotionReview, emotionIntensityReview, emotionDetailsReview));
        List<String> inputs =  new ArrayList<>(Arrays. asList(tjViewModel.getLocationText(), tjViewModel.getTimeText(), tjViewModel.getPeopleText(),tjViewModel.getSituationText(), tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(), tjViewModel.getEmotionRatingString(), tjViewModel.getEmotionDetailText()));

        for(int i = 0; i < textFields.size(); i++){
            if(!inputs.get(i).equals("")){
                textFields.get(i).setText(inputs.get(i));
                textFields.get(i).setTextColor(getResources().getColor(R.color.lightGrey));
            }
        }

        if(!tjViewModel.getThoughtText().equals("")){
            String[] thoughtList = tjViewModel.getThoughtText().split("\\s*,\\s*");
            for(String thought: thoughtList){
                thoughts += "-" + thought + "\n";
            }
            thoughtsReview.setText(thoughts);
            thoughtsReview.setTextColor(getResources().getColor(R.color.lightGrey));
        }
    }

    private void setButtons() {
        editButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.popBackStack(R.id.tjPageFive, true);
            controller.navigate(R.id.tjPageFive);

        });
        submitButton.setOnClickListener(v ->{
            List<String> requiredInputs =  new ArrayList<>(Arrays. asList(tjViewModel.getLocationText(), tjViewModel.getTimeText(), tjViewModel.getPeopleText(), tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(), tjViewModel.getEmotionRatingString(), tjViewModel.getThoughtText()));

            if(requiredInputs.contains("")){
                Toast.makeText(requireActivity().getApplicationContext(), "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
            }
            else{
                submitData();
                Toast.makeText(requireActivity().getApplicationContext(), "Saved in logs", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.requireActivity(), MainActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }


    private void submitData() {
       ((TJActivity)requireActivity()).sendToFirestore(tjViewModel.getLocationText(),tjViewModel.getTimeText(),tjViewModel.getPeopleText(),tjViewModel.getSituationText(),tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(),tjViewModel.getEmotionRating(),tjViewModel.getEmotionDetailText(), tjViewModel.getThoughtText());
    }
}