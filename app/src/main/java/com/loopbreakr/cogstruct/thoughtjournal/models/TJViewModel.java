package com.loopbreakr.cogstruct.thoughtjournal.models;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;

import java.util.ArrayList;
import java.util.List;

public class TJViewModel extends ViewModel {
    private String locationText;
    private String timeText;
    private String peopleText;
    private String situationText;
    private String behaviorText;
    private String emotionText;
    private String emotionDetailText;
    private int timeRadioId = -1;
    private int peopleRadioId = -1;
    private int emotionRadioId = -1;
    private float emotionRating = 0;
    private String emotionRatingString;
    private List<String> thoughtList = new ArrayList<>();

    //enable/disable people edittext based on radiobutton option
    private boolean tjIsEnabled = true;

//dummy value for review display
    private String displayThoughts;


    public void setLocationText(String input) { locationText = input; }
    public void setTimeText(String input) { timeText = input; }
    public void setPeopleText(String input) { peopleText = input; }
    public void setSituationText(String input){ situationText = input; }
    public void setBehaviorText(String input){ behaviorText = input;}
    public void setEmotionText(String input){ emotionText = input;}
    public void setEmotionDetailText(String input){ emotionDetailText = input; }

    public void setThoughtList(List<String> input){ thoughtList = input;
        StringBuilder displayText = new StringBuilder();
        for(String thought: thoughtList){
            displayText.append("-").append(thought).append("\n");
        }
        setDisplayThoughts(displayText.toString());
    }

    public void setTimeRadioId(int input) {
        timeRadioId = input;
        if(input == R.id.morning) {setTimeText("Morning");}
        else if(input == R.id.noonish){ setTimeText("Noonish");}
        else if(input == R.id.afternoon){ setTimeText("Afternoon");}
        else if(input == R.id.evening){setTimeText("Evening");}
        else if(input == R.id.night){setTimeText("At night");}
    }
    public void setPeopleRadioId(int input) {
        if(input == R.id.alone) {
            setPeopleText("Alone");
            setTjIsEnabled(false);}
        else{
            setTjIsEnabled(true);
        }
        peopleRadioId = input;
    }
    public void setEmotionRadioId(int input) {
        emotionRadioId = input;
        if(input == R.id.happiness) {setEmotionText("Happiness");}
        else if(input == R.id.sadness){ setEmotionText("Sadness");}
        else if(input == R.id.anger){ setEmotionText("Anger");}
        else if(input == R.id.fear){setEmotionText("Fear");}
    }
    public void setEmotionRating(float input) {
        emotionRating = input;
        setEmotionRatingString(Float.toString(input));
    }
    public void setTjIsEnabled(boolean input){
        tjIsEnabled = input;
    }

    public void setEmotionRatingString(String input){ emotionRatingString = input; }
    public String getLocationText() { return locationText; }
    public String getTimeText(){ return timeText; }
    public String getPeopleText() {return  peopleText;}
    public String getSituationText(){ return situationText; }
    public String getBehaviorText(){ return behaviorText; }
    public String getEmotionText(){ return emotionText; }
    public String getEmotionDetailText(){ return emotionDetailText; }
    public List<String> getThoughtList() { return thoughtList; }
    public int getTimeRadioId() { return timeRadioId; }
    public int getPeopleRadioId() { return peopleRadioId; }
    public int getEmotionRadioId() { return emotionRadioId; }
    public float getEmotionRating() { return emotionRating; }
    public String getEmotionRatingString(){ return emotionRatingString;}
    public String getThoughtText(){ return android.text.TextUtils.join(",", thoughtList); }

    public boolean isTjIsEnabled() { return tjIsEnabled; }


    //dummy display getter/setters
    public void setDisplayThoughts(String input) { displayThoughts = input; }
    public String getDisplayThoughts() { return displayThoughts; }
}