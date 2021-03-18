package com.loopbreakr.cogstruct.logs.models;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TJLogViewModel extends ViewModel {

    private ThoughtJournalObject thoughtJournal;

    private String tjLogLocation;
    private String tjLogTime;
    private String tjLogPeople;
    private String tjLogPeopleDisplay;
    private String tjLogSituation;
    private String tjLogBehavior;
    private String tjLogEmotion;
    private float tjLogEmotionRating;
    private String tjLogEmotionDetail;
    private List<String> tjLogThoughtList = new ArrayList<>();

    //dummy value for review display
    private String tjLogDisplayThoughts;
    private String tjLogDisplayEmotionRating;


    private int tjLogTimeRadioId;
    private int tjLogPeopleRadioId;
    private int tjLogEmotionRadioId;


    public void setThoughtJournal(ThoughtJournalObject thoughtJournal) {
        tjLogLocation = thoughtJournal.getLocation();
        tjLogTime = thoughtJournal.getTime();
        tjLogPeople = thoughtJournal.getPeople();
        tjLogSituation = thoughtJournal.getSituation();
        tjLogBehavior = thoughtJournal.getBehavior();
        tjLogEmotion = thoughtJournal.getEmotion();
        tjLogEmotionRating = thoughtJournal.getEmotionRating();
        tjLogDisplayEmotionRating = Float.toString(tjLogEmotionRating);
        tjLogEmotionDetail = thoughtJournal.getEmotionDetail();

        setTjLogThoughtList(Arrays.asList((thoughtJournal.getThoughts()).split("\\s*,\\s*")));
        initialRadioButtonSet(tjLogTime, tjLogPeople, tjLogEmotion);
    }

    //initialize radio button options when receiving FB data
    private void initialRadioButtonSet(String tjLogTime, String tjLogPeople, String tjLogEmotion) {
        switch (tjLogTime){
            case "Morning":
                tjLogTimeRadioId = R.id.morning_log;
                break;
            case "Noonish":
                tjLogTimeRadioId = R.id.noonish_log;
                break;
            case "Afternoon":
                tjLogTimeRadioId = R.id.afternoon_log;
                break;
            case "Evening":
                tjLogTimeRadioId =R.id.evening_log;
                break;
            case "At night":
                tjLogTimeRadioId = R.id.night_log;
                break;
        }

        switch (tjLogEmotion){
            case "Sadness":
                tjLogEmotionRadioId = R.id.sadness_log;
                break;
            case "Happiness":
                tjLogEmotionRadioId = R.id.happiness_log;
                break;
            case "Anger":
                tjLogEmotionRadioId = R.id.anger_log;
                break;
            case "Fear":
                tjLogEmotionRadioId = R.id.fear_log;
                break;
        }

        if(tjLogPeople.equals("Alone")){
            tjLogPeopleRadioId = R.id.alone_log;
        }
        else{
            tjLogPeopleRadioId = R.id.with_othersLog;
            setTjLogPeopleDisplay(tjLogPeople);
        }
    }
    public void setTjLogPeopleDisplay(String input){ tjLogPeopleDisplay = input;
    if(tjLogPeopleRadioId == R.id.with_othersLog){ setTjLogPeople(input);}}

    public void setTjLogLocation(String input) { tjLogLocation = input; }
    public void setTjLogTime(String input) { tjLogTime = input; }
    public void setTjLogPeople(String input) { tjLogPeople = input; }
    public void setTjLogSituation(String input){ tjLogSituation = input; }
    public void setTjLogBehavior(String input){ tjLogBehavior = input;}
    public void setTjLogEmotion(String input){ tjLogEmotion = input;}
    public void setTjLogEmotionDetail(String input){ tjLogEmotionDetail = input; }

    public void setTjLogTimeRadioId(int input) {
        tjLogTimeRadioId = input;
        if(input == R.id.morning_log) {setTjLogTime("Morning");}
        else if(input == R.id.noonish_log){ setTjLogTime("Noonish");}
        else if(input == R.id.afternoon_log){ setTjLogTime("Afternoon");}
        else if(input == R.id.evening_log){setTjLogTime("Evening");}
        else if(input == R.id.night_log){setTjLogTime("At night");}
    }
    public void setTjLogPeopleRadioId(int input) {
        if(input == R.id.alone_log) {
            setTjLogPeople("Alone");
        }

        tjLogPeopleRadioId = input;
    }
    public void  setTjLogEmotionRadioId(int input) {
        tjLogEmotionRadioId= input;
        if(input == R.id.happiness) {setTjLogEmotion("Happiness");}
        else if(input == R.id.sadness){ setTjLogEmotion("Sadness");}
        else if(input == R.id.anger){ setTjLogEmotion("Anger");}
        else if(input == R.id.fear){setTjLogEmotion("Fear");}
    }
    public void setTjLogEmotionRating(float input) {
        tjLogEmotionRating = input;
        setTjLogDisplayEmotionRating(Float.toString(input));
    }
    public void setTjLogDisplayEmotionRating(String input) { tjLogDisplayEmotionRating = input; }

    public void setTjLogThoughtList(List<String> input){ tjLogThoughtList = input;
        StringBuilder displayText = new StringBuilder();
        for(String thought: tjLogThoughtList){
            displayText.append("-").append(thought).append("\n");
        }
        setTjLogDisplayThoughts(displayText.toString());
    }

    public String getTjLogLocation() { return tjLogLocation; }
    public String getTjLogTime() { return tjLogTime; }
    public String getTjLogPeople() { return tjLogPeople; }
    public String getTjLogPeopleDisplay() { return tjLogPeopleDisplay; }
    public String getTjLogSituation() { return tjLogSituation; }
    public String getTjLogBehavior() { return tjLogBehavior; }
    public String getTjLogEmotion() { return tjLogEmotion; }
    public float getTjLogEmotionRating() { return tjLogEmotionRating; }
    public String getTjLogEmotionDetail() { return tjLogEmotionDetail; }
    public int getTjLogTimeRadioId() { return tjLogTimeRadioId; }
    public int getTjLogPeopleRadioId() { return tjLogPeopleRadioId; }
    public int getTjLogEmotionRadioId() { return tjLogEmotionRadioId; }
    public String getTjLogDisplayEmotionRating() { return tjLogDisplayEmotionRating; }

    //dummy display getter/setters
    public void setTjLogDisplayThoughts(String input) { tjLogDisplayThoughts = input; }
    public String getTjLogDisplayThoughts() { return tjLogDisplayThoughts; }
}
