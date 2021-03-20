package com.loopbreakr.cogstruct.howdigethere;

public class HIGHViewModel {

    private String highBehavior;
    private String highPromptingEvent;


    public void setHIGHLog(HIGHObject highLog){
    }

    public void setHighBehavior(String input){ highBehavior = input; }
    public void setHighPromptingEvent(String input) { highPromptingEvent = input; }

    public String getHighBehavior(){return highBehavior;}
    public String getHighPromptingEvent(){ return highPromptingEvent;}

}
