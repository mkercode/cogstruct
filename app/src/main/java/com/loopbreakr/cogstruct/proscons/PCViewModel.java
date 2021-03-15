package com.loopbreakr.cogstruct.proscons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class PCViewModel extends ViewModel {

    private MutableLiveData<CharSequence> behaviorText = new MutableLiveData<>();
    private List<String> changePros = new ArrayList<>();
    private List<String> changeCons = new ArrayList<>();
    private List<String> dontChangePros = new ArrayList<>();
    private List<String> dontChangeCons = new ArrayList<>();

    public void setPCBehavior(CharSequence behavior) { behaviorText.setValue(behavior); }
    public void setChangePros(List<String> input){ changePros = input; }
    public void setChangeCons(List<String> input){ changeCons = input; }
    public void setDontChangePros(List<String> input){ dontChangePros = input; }
    public void setDontChangeCons(List<String> input){ dontChangeCons = input; }

    public LiveData<CharSequence> getPCBehavior() { return behaviorText; }
    public List<String> getChangePros() { return changePros; }
    public List<String> getChangeCons() { return changeCons; }
    public List<String> getDontChangePros() { return dontChangePros; }
    public List<String> getDontChangeCons() { return dontChangeCons; }

    public String getChangeProsString() { return android.text.TextUtils.join(",", changePros); }
    public String getChangeConsString() { return android.text.TextUtils.join(",", changeCons); }
    public String getDontChangeProsString() { return android.text.TextUtils.join(",", dontChangePros); }
    public String getDontChangeConsString() { return android.text.TextUtils.join(",", dontChangeCons); }
}
