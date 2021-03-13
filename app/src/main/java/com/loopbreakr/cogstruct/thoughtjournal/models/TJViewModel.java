package com.loopbreakr.cogstruct.thoughtjournal.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class TJViewModel extends ViewModel {
    private String locationText = "";
    private String timeText = "";
    private String peopleText = "";
    private String situationText = "";
    private String behaviorText = "";
    private String emotionText = "";
    private String emotionDetailText = "";
    private String thoughtText;
    private int timeRadioId = -1;
    private int peopleRadioId = -1;
    private int emotionRadioId = -1;
    private float emotionRating = 0;
    private String emotionRatingString = "";
    private List<String> thoughtList = new ArrayList<>();

    public void setLocationText(CharSequence input) {
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
    public void setEmotionDetailText(CharSequence input){ emotionDetailText = input.toString(); }
    public void setTimeRadioId(int input) { timeRadioId = input; }
    public void setPeopleRadioId(int input) { peopleRadioId = input; }
    public void setEmotionRadioId(int input) { emotionRadioId = input; }
    public void setThoughtList(List<String> input){ thoughtList = input; }

    public void setEmotionRating(float input) {
        emotionRating = input;
        emotionRatingString = Float.toString(input);}

    public String getLocationText() {
        return locationText;
    }
    public String getTimeText(){ return timeText; }
    public String getPeopleText() {return  peopleText;}
    public String getSituationText(){ return situationText; }
    public String getBehaviorText(){ return behaviorText; }
    public String getEmotionText(){ return emotionText; }
    public String getEmotionDetailText(){ return emotionDetailText; }
    public List<String> getThoughtList() { return thoughtList; }
    public String getThoughtText(){ return android.text.TextUtils.join(",", thoughtList); }
    public int getTimeRadioId() { return timeRadioId; }
    public int getPeopleRadioId() { return peopleRadioId; }
    public int getEmotionRadioId() { return emotionRadioId; }
    public float getEmotionRating() { return emotionRating; }


    public String getEmotionRatingString(){
        return emotionRatingString;
    }


}