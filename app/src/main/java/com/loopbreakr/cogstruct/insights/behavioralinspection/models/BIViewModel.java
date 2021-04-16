package com.loopbreakr.cogstruct.insights.behavioralinspection.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;

import com.google.common.collect.HashMultimap;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.insights.objects.Factor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import kotlin.collections.builders.MapBuilder;

public class BIViewModel extends ViewModel {
    private List<DocumentSnapshot> biSnapShotList = new ArrayList<>();
    private List<String> biBehaviorList;
    private List<String> biInspectionList;
    private String biBehavior;
    private String biInspection;
    private String biInspectionDisplay;


    public void setBiSnapshotList(List<DocumentSnapshot> input) {
        biSnapShotList = input;
        setBiBehaviorList(biSnapShotList);}

    public void setBiBehavior(String input){ biBehavior = input; }

    public List<String> getBiInspectionList(List<DocumentSnapshot> snapshotList, String behavior) {
        biInspectionList = new ArrayList<>();
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
            if (snapshot.getString("behavior").equals(behavior) && snapshot.contains(biInspection)) {
                String tempString = snapshot.getString(biInspection).trim().toLowerCase();
                Integer tempInt = factorMap.get(tempString);
                factorMap.put(tempString, (tempInt== null) ? 1 : tempInt + 1);
            }
        }
        return factorMap;
    }

    public String getBiBehavior(){return biBehavior;}
    public String getBiInspection(){return biInspection;}
    public List<DocumentSnapshot> getBiSnapShotList() { return biSnapShotList; }
    public List<String> getBiBehaviorList(){ return biBehaviorList; }
    public String getBiInspectionDisplay(){return biInspectionDisplay;}

    private void filterList(List<String> tempBehaviorList) {
        List<String> tempList = new ArrayList<>(new LinkedHashSet<>(tempBehaviorList));
        Set<String> tempSet = new LinkedHashSet<>(tempList);
        biBehaviorList.clear();
        biBehaviorList.addAll(tempSet);
    }

    private void countFrequencies(ArrayList<String> list){


    }

}

