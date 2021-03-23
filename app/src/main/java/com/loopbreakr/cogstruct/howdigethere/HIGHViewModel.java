package com.loopbreakr.cogstruct.howdigethere;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.howdigethere.objects.HIGHObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HIGHViewModel extends ViewModel {

    private String highBehavior;
    private String highTriggerEvent;
    private String highEmotion;
    private float highEmotionIntensity;
    private String highEmotionIntensityString;
    private int highEmotionRadioId;

    private List<String> highThoughts = new ArrayList<>();
    private List<String> highVulnerabilities = new ArrayList<>();
    private List<String> highReliefs = new ArrayList<>();
    private List<String> highConsequences = new ArrayList<>();
    private List<String> highSolutions = new ArrayList<>();
    private List<String> highRepairs = new ArrayList<>();

    private String displayHighThoughts;
    private String displayHighVulnerabilities;
    private String displayHighReliefs;
    private String displayHighConsequences;
    private String displayHighSolutions;
    private String displayHighRepairs;


    public void setHIGHLog(HIGHObject highLog){
        setHighBehavior(highLog.getBehavior());
        setHighTriggerEvent(highLog.getTriggerEvent());
        setHighEmotion(highLog.getEmotion());
        setHighEmotionIntensity(highLog.getEmotionRating());
        setHighThoughts(Arrays.asList(highLog.getThoughts().split("\\s*,\\s*")));
        setHighVulnerabilities(Arrays.asList(highLog.getVulnerabilities().split("\\s*,\\s*")));
        setHighReliefs(Arrays.asList(highLog.getReliefs().split("\\s*,\\s*")));
        setHighConsequences(Arrays.asList(highLog.getConsequences().split("\\s*,\\s*")));
        setHighSolutions(Arrays.asList(highLog.getSolutions().split("\\s*,\\s*")));
        setHighRepairs(Arrays.asList(highLog.getRepairs().split("\\s*,\\s*")));

        initializeRadioId(highLog.getEmotion());
    }

    private void initializeRadioId(String emotion) {
        if (emotion.equals("Sadness")) {
            highEmotionRadioId = R.id.high_sadness_log;
        }
        else if(emotion.equals("Anger")){
            highEmotionRadioId = R.id.high_anger_log;
        }
        else if(emotion.equals("Happiness")){
            highEmotionRadioId = R.id.high_happiness_log;
        }
        else if(emotion.equals("Fear")){
            highEmotionRadioId = R.id.high_fear_log;
        }
    }

    public void setHighBehavior(String input){ highBehavior = input; }
    public void setHighTriggerEvent(String input){ highTriggerEvent = input; }
    public void setHighEmotion(String input){highEmotion = input;}
    public void setHighEmotionIntensity(float input){highEmotionIntensity = input;
        setHighEmotionIntensityString(Float.toString(input));}
    public void setHighEmotionIntensityString(String input){highEmotionIntensityString = input;}

    public void setHighEmotionRadioId(int input){highEmotionRadioId = input;
    if(input == R.id.high_sadness || input == R.id.high_sadness_log){
        setHighEmotion("Sadness");
    }
    else if(input == R.id.high_anger || input == R.id.high_anger_log){
        setHighEmotion("Anger");
    }
    else if(input == R.id.high_happiness || input == R.id.high_happiness_log){
        setHighEmotion("Happiness");
    }
    else if(input == R.id.high_fear || input == R.id.high_fear_log){
        setHighEmotion("Fear");
    }
    }

    public void setHighThoughts(List<String> input) { highThoughts = input;}
    public void setHighVulnerabilities(List<String> input) { highVulnerabilities = input;}
    public void setHighReliefs(List<String> input) { highReliefs = input;}
    public void setHighConsequences(List<String> input) { highConsequences = input;}
    public void setHighSolutions(List<String> input) { highSolutions = input;}
    public void setHighRepairs(List<String> input) { highRepairs = input; }

    public void setDisplayHighThoughts(String input) { displayHighThoughts = input; }
    public void setDisplayHighVulnerabilities(String input) { displayHighVulnerabilities = input; }
    public void setDisplayHighReliefs(String input) { displayHighReliefs = input; }
    public void setDisplayHighConsequences(String input) { displayHighConsequences = input; }
    public void setDisplayHighSolutions(String input) { displayHighSolutions = input; }
    public void setDisplayHighRepairs(String input) { displayHighRepairs = input; }


    public String getHighBehavior(){return highBehavior;}
    public String getHighTriggerEvent(){return highTriggerEvent;}
    public float getHighEmotionIntensity() { return highEmotionIntensity; }
    public String getHighEmotion(){return highEmotion;}
    public String getHighEmotionIntensityString(){return highEmotionIntensityString;}
    public int getHighEmotionRadioId(){return highEmotionRadioId;}


    public List<String> getHighThoughts() { return highThoughts; }
    public List<String> getHighVulnerabilities() { return highVulnerabilities; }
    public List<String> getHighReliefs() { return highReliefs; }
    public List<String> getHighConsequences() { return highConsequences; }
    public List<String> getHighSolutions() { return highSolutions; }
    public List<String> getHighRepairs() { return highRepairs; }
    public String getDisplayHighThoughts() { return createDisplayList(highThoughts); }
    public String getDisplayHighVulnerabilities() { return createDisplayList(highVulnerabilities); }
    public String getDisplayHighReliefs() { return createDisplayList(highReliefs); }
    public String getDisplayHighConsequences() { return createDisplayList(highConsequences); }
    public String getDisplayHighSolutions() { return createDisplayList(highSolutions); }
    public String getDisplayHighRepairs() { return createDisplayList(highRepairs); }

    public String getHighThoughtsString() { return android.text.TextUtils.join(",", highThoughts); }
    public String getHighVulnerabilitiesString() { return android.text.TextUtils.join(",", highVulnerabilities); }
    public String getHighReliefsString() { return android.text.TextUtils.join(",", highReliefs); }
    public String getHighConsequencesString() { return android.text.TextUtils.join(",", highConsequences); }
    public String getHighSolutionsString() { return android.text.TextUtils.join(",", highSolutions); }
    public String getHighRepairsString() { return android.text.TextUtils.join(",", highRepairs); }

    public String createDisplayList(List<String> list){
        StringBuilder displayText = new StringBuilder();
        for(String item: list){
            displayText.append("-").append(item).append("\n");
        }
        return displayText.toString();
    }

}
