package com.loopbreakr.cogstruct.badbehaviors.objects;

public class BBObject {

    private String formName;
    private String dateCreated;
    private String userId;
    private String behavior;
    private String environmentals;
    private String distractions;
    private String solutions;
    private String timeStamp;

    public BBObject() {
    }

    public BBObject(String dateCreated, String userId, String behavior, String environmentals, String distractions, String solutions, String timeStamp){
        this.formName = "Bad Behaviors";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.behavior = behavior;
        this.environmentals = environmentals;
        this.distractions = distractions;
        this.solutions = solutions;
        this.timeStamp = timeStamp;
    }

    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public String getEnvironmentals() { return environmentals; }
    public void setEnvironmentals(String environmentals) { this.environmentals = environmentals; }
    public String getDistractions() { return distractions; }
    public void setDistractions(String distractions) { this.distractions = distractions; }
    public String getSolutions() { return solutions; }
    public void setSolutions(String solutions) { this.solutions = solutions; }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
