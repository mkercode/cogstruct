package com.loopbreakr.cogstruct.insights.behavioralinspection.models;


import androidx.lifecycle.ViewModel;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BIViewModel extends ViewModel {
    private List<DocumentSnapshot> biSnapShotList = new ArrayList<>();
    private List<String> biBehaviorList;
    private String biBehavior;
    private String biInspection;
    private String biInspectionDisplay;


    public void setBiSnapshotList(List<DocumentSnapshot> input) {
        biSnapShotList = input;
        setBiBehaviorList(biSnapShotList);}

    public void setBiBehaviorList(List<DocumentSnapshot> snapshotList){
        biBehaviorList = new ArrayList<>();
        for(DocumentSnapshot snapshot: snapshotList){
            String currBehavior = snapshot.getString("behavior").trim();
            if(currBehavior != null && !currBehavior.isEmpty() && !biBehaviorList.contains(currBehavior)){
                biBehaviorList.add(currBehavior);
            }
        }
    }

    public void setBiBehavior(String input){ biBehavior = input; }

    public List<String> getBiInspectionList(List<DocumentSnapshot> snapshotList, String behavior) {
        List<String> biInspectionList = new ArrayList<>();
        for (DocumentSnapshot snapshot : snapshotList) {
            if (snapshot.getString("behavior").equals(behavior)) {
                //check document snapshots that equal that behavior for relevant inspection variables, add them to the list
                if (snapshot.contains("triggerEvent") && !biInspectionList.contains("Triggers")) {
                    biInspectionList.add("Triggers");
                }
                if (snapshot.contains("vulnerabilities") && !biInspectionList.contains("Vulnerabilities")) {
                    biInspectionList.add("Vulnerabilities");
                }
                if (snapshot.contains("thoughts") && !biInspectionList.contains("Thoughts")) {
                    biInspectionList.add("Thoughts");
                }
                if (snapshot.contains("emotion") && !biInspectionList.contains("Emotions")) {
                    biInspectionList.add("Emotions");
                }
                if (snapshot.contains("people") && !biInspectionList.contains("People")) {
                    biInspectionList.add("People");
                }
                if (snapshot.contains("location") && !biInspectionList.contains("Places")) {
                    biInspectionList.add("Places");
                }
            }
        }
        return biInspectionList;
    }

    public void setBiInspection(String input){
        switch (input){
            case "Triggers":
                biInspection = "triggerEvent";
                break;
            case "Vulnerabilities":
                biInspection = "vulnerabilities";
                break;
            case "Thoughts":
                biInspection = "thoughts";
                break;
            case "Emotions":
                biInspection = "emotion";
                break;
            case "People":
                biInspection = "people";
                break;
            case "Places":
                biInspection = "location";
                break;
        }
        setBiInspectionDisplay(input);
    }
    public void setBiInspectionDisplay(String input){ biInspectionDisplay = input; }


    public Map<String, Integer> getBiFactorList(List<DocumentSnapshot> snapshotList, String behavior, String biInspection) {
        Map<String, Integer> factorMap = new HashMap<>();
        for (DocumentSnapshot snapshot : snapshotList) {
            //check if snapshot matches user option and contains the field
            if (snapshot.getString("behavior").equals(behavior) && snapshot.contains(biInspection)) {
                String tempString = snapshot.getString(biInspection);
                Integer tempInt = factorMap.get(tempString);

                //check if the data contains field in the firestore DB which stores comma separated strings exclusively
                if (biInspection.equals("thoughts") || biInspection.equals("vulnerabilities")) {
                    //split the comma separated values
                    String[] tempList = tempString.split("\\s*,\\s*");
                    for(String string: tempList){
                        //add k/v to hashmap
                        factorMap.put(string.trim().toLowerCase(), (tempInt== null) ? 1 : tempInt + 1);
                    }
                }
                //if not a field which contains comma separated strings, just add values to map
                else{
                    //add k/v to hashmap
                    factorMap.put(tempString.trim().toLowerCase(), (tempInt== null) ? 1 : tempInt + 1);
                }
            }
        }
        return factorMap;
    }

    public List<DocumentSnapshot> getBiSnapShotList() { return biSnapShotList; }
    public List<String> getBiBehaviorList(){ return biBehaviorList; }
    public String getBiBehavior(){return biBehavior;}
    public String getBiInspection(){return biInspection;}
    public String getBiInspectionDisplay(){return biInspectionDisplay;}
}

