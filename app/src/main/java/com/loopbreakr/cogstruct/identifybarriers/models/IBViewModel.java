package com.loopbreakr.cogstruct.identifybarriers.models;

import androidx.lifecycle.ViewModel;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.identifybarriers.objects.IBObject;

public class IBViewModel extends ViewModel {
    private String ibBehavior;
    private String ibNescessaryAction;
    private String ibWillingBarrier;
    private String ibThinkingBarrier;
    private String ibDoingBarrier;
    private int ibWillingRadioId;
    private int ibThinkingRadioId;

    private String ibBarrierType = "";

    private String ibBarrier;
    private String ibSolution;

    private int ibBarrierTypeRadioId;

    //enable/disable people edittext based on radiobutton option
    private boolean ibWillingIsEnabled = true;
    private boolean ibThinkingIsEnabled = true;
    
    public void setIBLog(IBObject ibObject){
        ibBehavior = ibObject.getBehavior();
        ibNescessaryAction = ibObject.getNecessaryAction();
        ibBarrierType = ibObject.getBarrierType();
        ibBarrier = ibObject.getBarrier();
        ibSolution = ibObject.getSolutions();

        initializeLogRadioButtons(ibBarrierType);
    }

    private void initializeLogRadioButtons(String barrierType) {
        setIbBarrierTypeRadioId(-1);
        switch (barrierType){
            case "Willingness":
                ibBarrierTypeRadioId = R.id.ib_will_type_log;
                break;
            case "Thought":
                ibBarrierTypeRadioId = R.id.ib_thought_type_log;
                break;
            case "Action":
                ibBarrierTypeRadioId = R.id.ib_action_type_log;
                break;
        }
    }
    public void setIbBarrierTypeRadioId(int input) {ibBarrierTypeRadioId = input;
        //set the radio id in IB log edit fragment
        if(input == R.id.ib_will_type_log){
            setIbBarrierType("Willingness");
        }
        else if(input == R.id.ib_thought_type_log){
            setIbBarrierType("Thought");
        }
        else if( input == R.id.ib_action_type_log){
            setIbBarrierType("Action");
        }
    }
    public int getIbBarrierTypeRadioId() { return ibBarrierTypeRadioId; }


    public void setIbBehavior(String input) { ibBehavior = input; }
    public void setIbNescessaryAction(String input) { ibNescessaryAction = input; }
    public void setIbWillingBarrier(String input) { ibWillingBarrier = input;
    setIbBarrier(input);}
    public void setIbThinkingBarrier(String input) { ibThinkingBarrier = input;
    setIbBarrier(input);}
    public void setIbDoingBarrier(String input) { ibDoingBarrier = input;
    setIbBarrier(input);}
    public void setIbBarrier(String input) { ibBarrier = input; }
    public void setIbBarrierType(String input){
        ibBarrierType = input;
    }


    public void setIbWillingRadioId(int input) {
        if(input == R.id.ib_willing_yes) {
            setIbWillingIsEnabled(false);
            setIbBarrierType("Action");}
        else if(input == R.id.ib_willing_no){
            setIbWillingIsEnabled(true);
            if(!ibBarrierType.equals("Willingness")){
                setIbBarrierType("Willingness"); }
            }
        ibWillingRadioId = input; }

    public void setIbThinkingRadioId(int input) {
        if(input == R.id.ib_thinking_yes) {
            setIbBarrierType("Action");
            setIbThinkingIsEnabled(false);
        }
        else if(input == R.id.ib_thinking_no){
            setIbThinkingIsEnabled(true);
            if(!ibBarrierType.equals("Thought")){
                setIbBarrierType("Thought"); }
            }
        ibThinkingRadioId = input; }

    public void setIbWillingIsEnabled(boolean input){
        ibWillingIsEnabled = input;
    }
    public void setIbThinkingIsEnabled(boolean input){
        ibThinkingIsEnabled = input;
    }

    public void setIbSolutions(String input){ ibSolution = input;}
    public String getIbSolutions() { return ibSolution; }
    public String getIbNescessaryAction() { return ibNescessaryAction; }
    public String getIbBehavior() { return ibBehavior; }
    public String getIbWillingBarrier() { return ibWillingBarrier; }
    public String getIbThinkingBarrier() { return ibThinkingBarrier; }
    public String getIbDoingBarrier() { return ibDoingBarrier; }
    public String getIbBarrierType() { return ibBarrierType; }
    public String getIbBarrier() { return ibBarrier;}
    public int getIbWillingRadioId() { return ibWillingRadioId; }
    public int getIbThinkingRadioId() { return ibThinkingRadioId; }


    public boolean isIbWillingIsEnabled() { return ibWillingIsEnabled; }
    public boolean isIbThinkingIsEnabled() { return ibThinkingIsEnabled; }

}
