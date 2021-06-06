package com.loopbreakr.cogstruct.insights.findthinkingerrors.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FTEViewModel extends ViewModel {
    private List<DocumentSnapshot> fteViewSnapShotList = new ArrayList<>();
    private List<DocumentSnapshot> fteCreateSnapShotList = new ArrayList<>();
    private DocumentSnapshot editDocumentSnapshot;
    private String fteThought;


    public void setFteViewSnapshotList(List<DocumentSnapshot> input) { fteViewSnapShotList = input; }
    public void setFteCreateSnapshotList(List<DocumentSnapshot> input) { fteCreateSnapShotList = input; }
    public void setFteThought(String input){ fteThought = input; }
    public void setEditDocumentSnapshot(DocumentSnapshot input){editDocumentSnapshot = input;}

    public List<String> getFteViewThoughtList(){
        List<String> fteThoughtViewList = new ArrayList<>();
        for(DocumentSnapshot snapshot: fteViewSnapShotList){
            if(snapshot.contains("thought")){
                String currThought = snapshot.getString("thought");
                if(!fteThoughtViewList.contains(currThought)){
                    fteThoughtViewList.add(currThought);
                }
            }
        }
        return fteThoughtViewList;
    }

    public List<String> getFteThoughtCreateList(){
        List<String> fteThoughtCreateList = new ArrayList<>();
        for(DocumentSnapshot snapshot: fteCreateSnapShotList){
            if(snapshot.contains("thoughts")){
                String tempString = snapshot.getString("thoughts");
                String[] tempList = tempString.split("\\s*,\\s*");

                for(String string: tempList){
                    if(!fteThoughtCreateList.contains(string.trim())){
                        fteThoughtCreateList.add(string.trim());
                    }
                }
            }
        }
        return fteThoughtCreateList;
    }

    public List<DocumentSnapshot> getFteViewSnapShotList() { return fteViewSnapShotList; }
    public List<DocumentSnapshot> getFteCreateSnapShotList() { return fteCreateSnapShotList; }
    public DocumentSnapshot getEditDocumentSnapshot(){return  editDocumentSnapshot;}

    public String getFteThought(){ return fteThought; }
}
