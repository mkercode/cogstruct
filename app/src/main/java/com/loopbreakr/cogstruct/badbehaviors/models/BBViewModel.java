package com.loopbreakr.cogstruct.badbehaviors.models;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.badbehaviors.objects.BBObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BBViewModel extends ViewModel {

    private String bbBehavior;

    private List<String> bbEnvironmentals = new ArrayList<>();
    private List<String> bbDistractions = new ArrayList<>();
    private List<String> bbSolutions = new ArrayList<>();

    private String displayBbEnvironmentals;
    private String displayBbDistractions;
    private String displayBbSolutions;

    public void setBBLog(BBObject bbLog){
        setBbBehavior(bbLog.getBehavior());
        setBbEnvironmentals(Arrays.asList(bbLog.getEnvironmentals().split("\\s*,\\s*")));
        setBbDistractions(Arrays.asList(bbLog.getDistractions().split("\\s*,\\s*")));
        setBbSolutions(Arrays.asList(bbLog.getSolutions().split("\\s*,\\s*")));
    }

    public void setBbBehavior(String input){ bbBehavior = input; }

    public void setBbEnvironmentals(List<String> input) { bbEnvironmentals = input; }
    public void setBbDistractions(List<String> input) { bbDistractions = input; }
    public void setBbSolutions(List<String> input) { bbSolutions = input; }

    public void setDisplayBbEnvironmentals(String input) { displayBbEnvironmentals = input; }
    public void setDisplayBbDistractions(String input) { displayBbDistractions = input; }
    public void setDisplayBbSolutions(String input) { displayBbSolutions = input; }

    public String getBbBehavior() { return bbBehavior; }
    public List<String> getBbEnvironmentals() { return bbEnvironmentals; }
    public List<String> getBbDistractions() { return bbDistractions; }
    public List<String> getBbSolutions() { return bbSolutions; }
    public String getDisplayBbEnvironmentals() { return createDisplayList(bbEnvironmentals); }
    public String getDisplayBbDistractions() { return createDisplayList(bbDistractions); }
    public String getDisplayBbSolutions() { return createDisplayList(bbSolutions); }


    public String getBbEnvironmentalsString() { return android.text.TextUtils.join(",", bbEnvironmentals); }
    public String getBbDistractionsString() { return android.text.TextUtils.join(",", bbDistractions); }
    public String getBbSolutionsString() { return android.text.TextUtils.join(",", bbSolutions); }



    public String createDisplayList(List<String> list){
        StringBuilder displayText = new StringBuilder();
        for(String item: list){
            displayText.append("-").append(item).append("\n");
        }
        return displayText.toString();
    }
}
