package com.loopbreakr.cogstruct.logs.objects;

public class LogsPreview {
    private String formName;
    private String dateCreated;
    private String userId;

    public LogsPreview(String formName, String dateCreated, String userId) {
        this.formName = formName;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    //required empty constructor for firebase
    public LogsPreview() { }
    public CharSequence getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
}

