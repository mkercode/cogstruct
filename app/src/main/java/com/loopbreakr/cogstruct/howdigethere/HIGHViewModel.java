package com.loopbreakr.cogstruct.howdigethere;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;

import java.util.ArrayList;
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
    }

    public void setHighBehavior(String input){ highBehavior = input; }
    public void setHighTriggerEvent(String input){ highTriggerEvent = input; }
    public void setHighEmotion(String input){highEmotion = input;}
    public void setHighEmotionIntensity(float input){highEmotionIntensity = input;
        setHighEmotionIntensityString(Float.toString(input));}
    public void setHighEmotionIntensityString(String input){highEmotionIntensityString = input;}

    public void setHighEmotionRadioId(int input){highEmotionRadioId = input;
    if(input == R.id.high_sadness){
        setHighEmotion("Sadness");
    }
    else if(input == R.id.high_anger){
        setHighEmotion("Anger");
    }
    else if(input == R.id.high_happiness){
        setHighEmotion("Happiness");
    }
    else if(input == R.id.high_fear){
        setHighEmotion("Fear");
    }
    }

    public void setHighThoughts(List<String> input) { highThoughts = input;
    setDisplayHighThoughts(createDisplayList(input));}
    public void setHighVulnerabilities(List<String> input) { highVulnerabilities = input;
    setDisplayHighVulnerabilities(createDisplayList(input));}
    public void setHighReliefs(List<String> input) { highReliefs = input;
    setDisplayHighReliefs(createDisplayList(input));}
    public void setHighConsequences(List<String> input) { highConsequences = input;
    setDisplayHighConsequences(createDisplayList(input));}
    public void setHighSolutions(List<String> input) { highSolutions = input;
    setDisplayHighSolutions(createDisplayList(input));}
    public void setHighRepairs(List<String> input) { highRepairs = input;
        setDisplayHighRepairs(createDisplayList(input));}

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
    public String getDisplayHighThoughts() { return displayHighThoughts; }
    public String getDisplayHighVulnerabilities() { return displayHighVulnerabilities; }
    public String getDisplayHighReliefs() { return displayHighReliefs; }
    public String getDisplayHighConsequences() { return displayHighConsequences; }
    public String getDisplayHighSolutions() { return displayHighSolutions; }
    public String getDisplayHighRepairs() { return displayHighRepairs; }

    public String getHighThoughtsString() { return android.text.TextUtils.join(",", highThoughts); }
    public String getHighVulnerabilitiesString() { return android.text.TextUtils.join(",", highVulnerabilities); }
    public String getHighReliefsString() { return android.text.TextUtils.join(",", highReliefs); }
    public String getHighConsequencesString() { return android.text.TextUtils.join(",", highConsequences); }
    public String getHighSolutionsString() { return android.text.TextUtils.join(",", highSolutions); }
    public String getHighRepairsString() { return android.text.TextUtils.join(",", highRepairs); }

    private String createDisplayList(List<String> items){
        StringBuilder displayText = new StringBuilder();
        for(String item: items){
            displayText.append("-").append(item).append("\n");
        }
        return displayText.toString();
    }

}
