package com.loopbreakr.cogstruct.logs;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

public class LogsViewModel extends ViewModel {
    private DocumentSnapshot snapshot;
    private Object form;

    public void setSnapshot(DocumentSnapshot input) {
        snapshot = input;
    }
    public DocumentSnapshot getSnapshot(){ return snapshot; }

    public Object getForm() { return form; }
    public void setForm(Object input) { form = input; }
}
