package com.loopbreakr.cogstruct.insights.findthinkingerrors.objects;

public class FTEObject {
    private String dateCreated;
    private String userId;
    private String thought;
    private String thinkingErrors;

    public FTEObject() {
    }

    public FTEObject(String dateCreated, String userId, String thought, String thinkingErrors) {
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.thought = thought;
        this.thinkingErrors = thinkingErrors;
    }

    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getThought() { return thought; }
    public void setThought(String thought) { this.thought = thought; }
    public String getThinkingErrors() { return thinkingErrors; }
    public void setThinkingErrors(String thinkingErrors) { this.thinkingErrors = thinkingErrors; }
}
