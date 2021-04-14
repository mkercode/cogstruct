package com.loopbreakr.cogstruct.behavioralinspection.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BIViewModel extends ViewModel {
    private List<DocumentSnapshot> biSnapShotList = new ArrayList<>();

    public void setBiSnapshotList(List<DocumentSnapshot> input) { biSnapShotList = input;}

    public List<DocumentSnapshot> getBiSnapShotList() { return biSnapShotList; }
}
