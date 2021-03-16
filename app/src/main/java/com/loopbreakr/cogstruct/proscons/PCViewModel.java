package com.loopbreakr.cogstruct.proscons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class PCViewModel extends ViewModel {

    private String behaviorText;


    private List<String> changePros = new ArrayList<>();
    private List<String> changeCons = new ArrayList<>();
    private List<String> dontChangePros = new ArrayList<>();
    private List<String> dontChangeCons = new ArrayList<>();


    public void setPCBehavior(String behavior) { behaviorText = behavior; }
    public void setChangePros(List<String> input){ changePros = input; }
    public void setChangeCons(List<String> input){ changeCons = input; }
    public void setDontChangePros(List<String> input){ dontChangePros = input; }
    public void setDontChangeCons(List<String> input){ dontChangeCons = input; }

    public String getPCBehavior() { return behaviorText; }
    public List<String> getChangePros() { return changePros; }
    public List<String> getChangeCons() { return changeCons; }
    public List<String> getDontChangePros() { return dontChangePros; }
    public List<String> getDontChangeCons() { return dontChangeCons; }

    public String getChangeProsString() { return android.text.TextUtils.join(",", changePros); }
    public String getChangeConsString() { return android.text.TextUtils.join(",", changeCons); }
    public String getDontChangeProsString() { return android.text.TextUtils.join(",", dontChangePros); }
    public String getDontChangeConsString() { return android.text.TextUtils.join(",", dontChangeCons); }

    public ArrayList<String> getDummyChangePros(){
        return new ArrayList<>(changePros);
    }
    public ArrayList<String> getDummyDontChangePros(){
        return new ArrayList<>(dontChangePros);
    }
    public ArrayList<String> getDummyChangeCons(){
        return new ArrayList<>(changeCons);
    }
    public ArrayList<String> getDummyDontChangeCons(){
        return new ArrayList<>(dontChangeCons);
    }
}
