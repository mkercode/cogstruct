package com.loopbreakr.brainstruct;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class TJViewModel extends ViewModel {
    private String locationText = "";
    private String timeText = "";
    private String peopleText = "";
    private String situationText = "";
    private String behaviorText = "";
    private String emotionText = "";
    private String emotionalActionText = "";
    private ArrayList<String> thoughtArray;



    public void setlocationText(CharSequence input) {
        locationText = input.toString();
    }

    public void setTimeText(CharSequence input) {
        timeText = input.toString();
    }

    public void setPeopleText(CharSequence input) {peopleText = input.toString();}

    public void setSituationText(CharSequence input){
        situationText = input.toString();
    }

    public void setBehaviorText(CharSequence input){
        behaviorText = input.toString();
    }

    public void setEmotionText(CharSequence input){
        emotionText = input.toString();
    }

    public void setEmotionalActionText(CharSequence input){ emotionalActionText = input.toString(); }

    public void setThoughtArray(ArrayList<String> input){ thoughtArray = input;
    }

    public String getLocationText() {
        return locationText;
    }
    public String getTimeText(){ return timeText; }
    public String getPeopleText() {return  peopleText;}
    public String getSituationText(){ return situationText; }
    public String getBehaviorText(){ return behaviorText; }
    public String getEmotionText(){ return emotionText; }
    public String getEmotionalActionText(){ return emotionalActionText; }
    public ArrayList<String> getThoughtArray(){ return thoughtArray; }

}
