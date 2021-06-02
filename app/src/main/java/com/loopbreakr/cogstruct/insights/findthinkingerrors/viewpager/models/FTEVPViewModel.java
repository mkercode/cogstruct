package com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FTEVPViewModel extends ViewModel {
    private String thought;
    private boolean bwtIsChecked;
    private boolean dpIsChecked;
    private boolean ffIsChecked;
    private boolean fotpIsChecked;
    private boolean genIsChecked;
    private boolean mfIsChecked;
    private boolean perIsChecked;
    private boolean ssIsChecked;

    private List<Boolean> checkedList;
    private List<String> nameList;

    private String displayList;

    public void setThought(String input){thought = input;}
    public String getThought(){ return thought;}
    public boolean isBwtIsChecked() {
        return bwtIsChecked;
    }
    public void setBwtIsChecked(boolean bwtIsChecked) {
        this.bwtIsChecked = bwtIsChecked;
    }
    public boolean isDpIsChecked() {
        return dpIsChecked;
    }
    public void setDpIsChecked(boolean dpIsChecked) {
        this.dpIsChecked = dpIsChecked;
    }
    public boolean isFfIsChecked() {
        return ffIsChecked;
    }
    public void setFfIsChecked(boolean ffIsChecked) {
        this.ffIsChecked = ffIsChecked;
    }
    public boolean isFotpIsChecked() {
        return fotpIsChecked;
    }
    public void setFotpIsChecked(boolean fotpIsChecked) {
        this.fotpIsChecked = fotpIsChecked;
    }
    public boolean isGenIsChecked() {
        return genIsChecked;
    }
    public void setGenIsChecked(boolean genIsChecked) {
        this.genIsChecked = genIsChecked;
    }
    public boolean isMfIsChecked() {
        return mfIsChecked;
    }
    public void setMfIsChecked(boolean mfIsChecked) {
        this.mfIsChecked = mfIsChecked;
    }
    public boolean isPerIsChecked() {
        return perIsChecked;
    }
    public void setPerIsChecked(boolean perIsChecked) {
        this.perIsChecked = perIsChecked;
    }
    public boolean isSsIsChecked() {
        return ssIsChecked;
    }
    public void setSsIsChecked(boolean ssIsChecked) {
        this.ssIsChecked = ssIsChecked;
    }

    public void setDisplayList(String stringData){
        displayList = "";
        if(!stringData.isEmpty()){
            String[] strings = stringData.split("\\s*,\\s*");
            for(String string: strings){
                displayList = displayList + "-" + string + "\n" + "\n";
            }
        }
    }

    public String getDisplayList(){return displayList;}

    public String getCheckedNameString(){
        List<String> selectedList = new ArrayList<>();

        checkedList = Arrays.asList(bwtIsChecked, dpIsChecked, ffIsChecked, fotpIsChecked, genIsChecked, mfIsChecked, perIsChecked, ssIsChecked);
        nameList = Arrays.asList("Black and White Thinking", "Disqualifying the Positive", "Fallacy of Fairness", "Focusing on the Past", "Generalization", "Mental Filter", "Personalization", "Should Statements");

        for(int i = 0; i < checkedList.size(); i++){
            if(checkedList.get(i)){
                selectedList.add(nameList.get(i));
            }
        }

        String selectedString = android.text.TextUtils.join(",",  selectedList);
        setDisplayList(selectedString);
        return selectedString;
    }

    public void initalizeData(String data){
        setDisplayList(data);

        setBwtIsChecked(data.contains("Black and White Thinking"));
        setDpIsChecked(data.contains("Disqualifying the Positive"));
        setFfIsChecked(data.contains("Fallacy of Fairness"));
        setFotpIsChecked(data.contains("Focusing on the Past"));
        setGenIsChecked(data.contains("Generalization"));
        setMfIsChecked(data.contains("Mental Filter"));
        setPerIsChecked(data.contains("Personalization"));
        setSsIsChecked(data.contains("Should Statements"));
    }


}
