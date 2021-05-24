package com.loopbreakr.cogstruct.identifybarriers.objects;

public class IBObject {

    private String formName;
    private String dateCreated;
    private String userId;
    private String behavior;
    private String necessaryAction;
    private String barrierType;
    private String barrier;
    private String solutions;
    private String timeStamp;

    public IBObject(){

    }

    public IBObject(String dateCreated, String userId, String behavior, String necessaryAction, String barrierType, String barrier, String solutions, String timeStamp) {
        this.formName = "Identify Barriers";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.behavior = behavior;
        this.necessaryAction = necessaryAction;
        this.barrierType = barrierType;
        this.barrier = barrier;
        this.solutions = solutions;
        this.timeStamp = timeStamp;
    }

    public void setFormName(String formName) { this.formName = formName; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public void setUserId(String userId) { this.userId = userId;}
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public void setNecessaryAction(String necessaryAction) { this.necessaryAction = necessaryAction; }
    public void setBarrierType(String barrierType) { this.barrierType = barrierType; }
    public void setBarrier(String barrier) { this.barrier = barrier; }
    public void setSolutions(String solutions) { this.solutions = solutions; }

    public String getFormName() { return formName; }
    public String getDateCreated() { return dateCreated; }
    public String getUserId() { return userId; }
    public String getBehavior() { return behavior; }
    public String getNecessaryAction() { return necessaryAction; }
    public String getBarrierType() { return barrierType; }
    public String getBarrier() { return barrier; }
    public String getSolutions() { return solutions; }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
