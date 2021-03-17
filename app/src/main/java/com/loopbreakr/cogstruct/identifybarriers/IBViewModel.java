package com.loopbreakr.cogstruct.identifybarriers;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;

import java.util.ArrayList;
import java.util.List;

public class IBViewModel extends ViewModel {
    private String ibBehavior;
    private String ibNescessaryAction;
    private String ibWillingBarrier;
    private String ibThinkingBarrier;
    private String ibDoingBarrier;
    private int ibWillingRadioId;
    private int ibThinkingRadioId;

    private String ibBarrierType;

    private String ibBarrier;
    private String ibProblemSolvingString;
    private List<String> ibProblemSolvingList = new ArrayList<>();

    //enable/disable people edittext based on radiobutton option
    private boolean ibWillingIsEnabled = true;
    private boolean ibThinkingIsEnabled = true;



    public void setIbBehavior(String input) { ibBehavior = input; }
    public void setIbNescessaryAction(String input) { ibNescessaryAction = input; }
    public void setIbWillingBarrier(String input) { ibWillingBarrier = input; }
    public void setIbThinkingBarrier(String input) { ibThinkingBarrier = input; }
    public void setIbDoingBarrier(String input) { ibDoingBarrier = input; }
    public void setIbBarrier(String input) { ibBarrier = input; }
    public void setIbBarrierType(String input){
        ibBarrierType = input;
    }


    public void setIbWillingRadioId(int input) {
        if(input == R.id.ib_willing_yes) {
            setIbWillingIsEnabled(false);}
        else if(input == R.id.ib_willing_no){
            setIbWillingIsEnabled(true);
            if(!ibBarrierType.equals("Willingness")){
                setIbBarrierType("Willingness");
                setIbBarrier("");}
            }
        ibWillingRadioId = input; }

    public void setIbThinkingRadioId(int input) {
        if(input == R.id.ib_thinking_yes) {
            setIbThinkingIsEnabled(false);
        }
        else if(input == R.id.ib_thinking_no){
            setIbThinkingIsEnabled(true);
            if(!ibBarrierType.equals("Thought")){
                setIbBarrierType("Thought");
                setIbBarrier("");}
            }
        ibThinkingRadioId = input; }

    public void setIbWillingIsEnabled(boolean input){
        ibWillingIsEnabled = input;
    }
    public void setIbThinkingIsEnabled(boolean input){
        ibThinkingIsEnabled = input;
    }

    public String getIbNescessaryAction() { return ibNescessaryAction; }
    public String getIbBehavior() { return ibBehavior; }
    public String getIbWillingBarrier() { return ibWillingBarrier; }
    public String getIbThinkingBarrier() { return ibThinkingBarrier; }
    public String getIbDoingBarrier() { return ibDoingBarrier; }

    public String getIbBarrierType() { return ibBarrierType; }

    public String getIbBarrier() {
    if (ibBarrierType.equals("Willingness")) {
        return ibWillingBarrier;
        }
    else if(ibBarrierType.equals("Thought")){
            return ibThinkingBarrier;
        }
    else{
    return ibDoingBarrier;}
    }

    public int getIbWillingRadioId() { return ibWillingRadioId; }
    public int getIbThinkingRadioId() { return ibThinkingRadioId; }

    public boolean isIbWillingIsEnabled() { return ibWillingIsEnabled; }
    public boolean isIbThinkingIsEnabled() { return ibThinkingIsEnabled; }


}
