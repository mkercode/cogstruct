package com.loopbreakr.cogstruct.logs.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

public class LogsViewModel extends ViewModel {
    private DocumentSnapshot snapshot;


    public void setSnapshot(DocumentSnapshot input) {
        snapshot = input;
    }
    public DocumentSnapshot getSnapshot(){ return snapshot; }


    public void clearAll(){
        snapshot = null;
    }


}
