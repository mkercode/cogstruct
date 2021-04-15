package com.loopbreakr.cogstruct.insights.behavioralinspection.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BIViewModel extends ViewModel {
    private List<DocumentSnapshot> biSnapShotList = new ArrayList<>();
    private List<String> biBehaviorList;

    public void setBiSnapshotList(List<DocumentSnapshot> input) {
        biSnapShotList = input;
        setBiBehaviorList(biSnapShotList);}

    public void setBiBehaviorList(List<DocumentSnapshot> snapshotList){
        biBehaviorList = new ArrayList<>();
        for(DocumentSnapshot snapshot: snapshotList){
            String currBehavior = snapshot.getString("behavior");
            if(currBehavior != null && !currBehavior.isEmpty()){
                biBehaviorList.add(currBehavior);
            }
        }
        filterList(biBehaviorList);
    }

    public List<DocumentSnapshot> getBiSnapShotList() { return biSnapShotList; }
    public List<String> getBiBehaviorList(){ return biBehaviorList; }

    private void filterList(List<String> tempBehaviorList) {
        List<String> tempList = new ArrayList<>(new LinkedHashSet<>(tempBehaviorList));
        Set<String> tempSet = new LinkedHashSet<>(tempList);
        biBehaviorList.clear();
        biBehaviorList.addAll(tempSet);
    }
}

