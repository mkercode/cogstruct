package com.loopbreakr.cogstruct.proscons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PCViewModel extends ViewModel {

    private ProsConsObject prosCons;

    private String behaviorText;

    private List<String> changePros = new ArrayList<>();
    private List<String> changeCons = new ArrayList<>();
    private List<String> dontChangePros = new ArrayList<>();
    private List<String> dontChangeCons = new ArrayList<>();

    private String displayChangePros;
    private String displayChangeCons;
    private String displayDontChangePros;
    private String displayDontChangeCons;

    public void setProsCons(ProsConsObject prosCons){
        setPCBehavior(prosCons.getBehavior());
        setChangePros(Arrays.asList(prosCons.getChangePros().split("\\s*,\\s*")));
        setDontChangePros(Arrays.asList(prosCons.getDontChangePros().split("\\s*,\\s*")));
        setChangeCons(Arrays.asList(prosCons.getChangeCons().split("\\s*,\\s*")));
        setDontChangeCons(Arrays.asList(prosCons.getDontChangeCons().split("\\s*,\\s*")));
    }

    public void setPCBehavior(String behavior) { behaviorText = behavior; }

    public void setChangePros(List<String> input){
        changePros = input; }
    public void setChangeCons(List<String> input){
        changeCons = input; }
    public void setDontChangePros(List<String> input){
        dontChangePros = input;
        setDisplayDontChangePros(createPCDisplayList(dontChangePros));}
    public void setDontChangeCons(List<String> input){
        dontChangeCons = input; }

    public String getPCBehavior() { return behaviorText; }
    public List<String> getChangePros() { return changePros; }
    public List<String> getChangeCons() { return changeCons; }
    public List<String> getDontChangePros() { return dontChangePros; }
    public List<String> getDontChangeCons() { return dontChangeCons; }

    public String getChangeProsString() { return android.text.TextUtils.join(",", changePros); }
    public String getChangeConsString() { return android.text.TextUtils.join(",", changeCons); }
    public String getDontChangeProsString() { return android.text.TextUtils.join(",", dontChangePros); }
    public String getDontChangeConsString() { return android.text.TextUtils.join(",", dontChangeCons); }


    //getters/setters/methods for displaying lists in review sections
    public void setDisplayChangePros(String input){ displayChangePros = input; }
    public void setDisplayChangeCons(String input){ displayChangeCons = input; }
    public void setDisplayDontChangePros(String input){ displayDontChangePros = input; }
    public void setDisplayDontChangeCons(String input){ displayDontChangeCons = input; }

    public String getDisplayChangePros() { return createPCDisplayList(changePros); }
    public String getDisplayChangeCons() { return createPCDisplayList(changeCons); }
    public String getDisplayDontChangePros() { return createPCDisplayList(dontChangePros); }
    public String getDisplayDontChangeCons() { return createPCDisplayList(dontChangeCons); }

    public String createPCDisplayList(List<String> list){
        StringBuilder displayText = new StringBuilder();
           for(String item: list){
               displayText.append("-").append(item).append("\n");
           }
           return displayText.toString();
    }
}
