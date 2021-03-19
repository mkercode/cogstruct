package com.loopbreakr.cogstruct.identifybarriers;

public class IBObject {

    private String formName;
    private String dateCreated;
    private String userId;
    private String behavior;
    private String nescessaryAction;
    private String barrierType;
    private String barrier;
    private String solution;

    public IBObject(String dateCreated, String userId, String behavior, String nescessaryAction, String barrierType, String barrier, String solution) {
        this.formName = "Pros and Cons";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.behavior = behavior;
        this.nescessaryAction = nescessaryAction;
        this.barrierType = barrierType;
        this.barrier = barrier;
        this.solution = solution;
    }

    public void setFormName(String formName) { this.formName = formName; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public void setNescessaryAction(String nescessaryAction) { this.nescessaryAction = nescessaryAction; }
    public void setBarrierType(String barrierType) { this.barrierType = barrierType; }
    public void setBarrier(String barrier) { this.barrier = barrier; }
    public void setSolution(String solution) { this.solution = solution; }

    public String getFormName() { return formName; }
    public String getDateCreated() { return dateCreated; }
    public String getUserId() { return userId; }
    public String getBehavior() { return behavior; }
    public String getNescessaryAction() { return nescessaryAction; }
    public String getBarrierType() { return barrierType; }
    public String getBarrier() { return barrier; }
    public String getSolution() { return solution; }
}
