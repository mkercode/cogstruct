package com.loopbreakr.cogstruct.logs.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

public class LogsViewModel extends ViewModel {
    private boolean runStatus = true;
    private String formFilter = "all";
    private DocumentSnapshot snapshot;

    public void setRunStatus(boolean runStatus) { this.runStatus = runStatus; }
    public void setFormFilter(String formFilter) { this.formFilter = formFilter; }
    public void setSnapshot(DocumentSnapshot input) {
        snapshot = input;
    }

    public boolean isRunStatus() { return runStatus; }
    public String getFormFilter() { return formFilter; }
    public DocumentSnapshot getSnapshot(){ return snapshot; }

    public void clearAll(){
        snapshot = null;
    }

}
