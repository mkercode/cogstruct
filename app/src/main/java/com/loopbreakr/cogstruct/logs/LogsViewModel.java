package com.loopbreakr.cogstruct.logs;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

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
