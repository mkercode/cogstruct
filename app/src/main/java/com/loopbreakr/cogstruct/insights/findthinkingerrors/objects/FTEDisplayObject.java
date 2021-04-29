package com.loopbreakr.cogstruct.insights.findthinkingerrors.objects;

import com.google.firebase.firestore.DocumentSnapshot;

public class FTEDisplayObject {
    private String dateCreated;
    private String userId;
    private String thought;
    private String thinkingErrors;
    private DocumentSnapshot snapshot;

    public FTEDisplayObject(String dateCreated, String userId, String thought, String thinkingErrors, DocumentSnapshot snapshot) {
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.thought = thought;
        this.thinkingErrors = thinkingErrors;
        this.snapshot = snapshot;
    }

    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getThought() { return thought; }
    public void setThought(String thought) { this.thought = thought; }
    public String getThinkingErrors() { return thinkingErrors; }
    public void setThinkingErrors(String thinkingErrors) { this.thinkingErrors = thinkingErrors; }
    public DocumentSnapshot getSnapshot() { return snapshot; }
    public void setSnapshot(DocumentSnapshot snapshot) { this.snapshot = snapshot; }
}
