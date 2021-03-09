package com.loopbreakr.cogstruct.logs;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

public class LogsViewModel extends ViewModel {
    private DocumentSnapshot snapshot;
    private ThoughtJournalObject thoughtJournalLog;


    public void setSnapshot(DocumentSnapshot input) {
        snapshot = input;
    }
    public DocumentSnapshot getSnapshot(){ return snapshot; }

    public void setThoughtJournalLog(ThoughtJournalObject input){ thoughtJournalLog = input;}
    public ThoughtJournalObject getThoughtJournalLog(){return thoughtJournalLog;}

    public void clearAll(){
        snapshot = null;
        thoughtJournalLog = null;
    }


}
