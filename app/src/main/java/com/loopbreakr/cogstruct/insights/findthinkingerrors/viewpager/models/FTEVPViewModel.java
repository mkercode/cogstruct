package com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FTEVPViewModel extends ViewModel {
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

    public String getCheckedNameString(){
        StringBuilder listString = new StringBuilder();
        checkedList = Arrays.asList(bwtIsChecked, dpIsChecked, ffIsChecked, fotpIsChecked, genIsChecked, mfIsChecked, perIsChecked, ssIsChecked);
        nameList = Arrays.asList("Black and White Thinking", "Disqualifying the Positive", "Fallacy of Fairness", "Focusing on the Past", "Generalization", "Mental Filter", "Personalization", "Should Statements");

        for(int i = 0; i < checkedList.size(); i++){
            if(checkedList.get(i)){
                if(i == (checkedList.size()-1)){
                    listString.append(nameList.get(i));
                }
                else{
                    listString.append(nameList.get(i)).append(",");
                }
            }
        }
        return listString.toString();
    }
}
