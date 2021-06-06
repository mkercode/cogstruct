package com.loopbreakr.cogstruct.insights.gameplan.models;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPViewModel extends ViewModel {
    private List<DocumentSnapshot> gpSnapShotList = new ArrayList<>();
    private List<String> gpBehaviorList;
    private String gpBehavior;
    private String gpChange;
    private String gpChangeDisplay;

    public void setGpSnapshotList(List<DocumentSnapshot> input) {
        gpSnapShotList = input;
        setGpBehaviorList(gpSnapShotList);}

    public void setGpBehaviorList(List<DocumentSnapshot> snapshotList){
        gpBehaviorList = new ArrayList<>();
        for(DocumentSnapshot snapshot: snapshotList){
            String currBehavior = snapshot.getString("behavior");
            if(currBehavior != null && !currBehavior.isEmpty() && !gpBehaviorList.contains(currBehavior)){
                gpBehaviorList.add(currBehavior);
            }
        }
    }
    public void setGpBehavior(String input){ gpBehavior = input; }

    public List<String> getGpChangeList(List<DocumentSnapshot> snapshotList, String behavior) {
        List<String> gpChangeList = new ArrayList<>();
        for (DocumentSnapshot snapshot : snapshotList) {
            if (snapshot.getString("behavior").equals(behavior)) {
                //check document snapshots that equal that behavior for relevant inspection variables, add them to the list
                if ((snapshot.contains("changePros") || snapshot.contains("changeCons") || snapshot.contains("dontChangePros") || snapshot.contains("dontChangeCons")) && !gpChangeList.contains("Pros/Cons")) {
                    gpChangeList.add("Pros/Cons");
                }
                if (snapshot.contains("vulnerabilities") && !gpChangeList.contains("Vulnerabilities")) {
                    gpChangeList.add("Vulnerabilities");
                }
                if (snapshot.contains("barrier") && !gpChangeList.contains("Barriers")) {
                    gpChangeList.add("Barriers");
                }
                if (snapshot.contains("distractions") && !gpChangeList.contains("Helpful Distractions")) {
                    gpChangeList.add("Helpful Distractions");
                }
                if (snapshot.contains("environmentals") && !gpChangeList.contains("Space Changes")) {
                    gpChangeList.add("Space Changes");
                }
                if (snapshot.contains("solutions") && !gpChangeList.contains("Solutions")) {
                    gpChangeList.add("Solutions");
                }
            }
        }
        return gpChangeList;
    }

    public void setGpChange(String input){
        switch (input){
            case "Vulnerabilities":
                gpChange = "vulnerabilities";
                break;
            case "Barriers":
                gpChange = "barrier";
                break;
            case "Helpful Distractions":
                gpChange = "distractions";
                break;
            case "Space Changes":
                gpChange = "environmentals";
                break;
            case "Solutions":
                gpChange = "solutions";
                break;

                //statements for pros/cons
            case "Change Pros":
                gpChange = "changePros";
                break;
            case "Change Cons":
                gpChange = "changeCons";
                break;
            case "Don't Change Pros":
                gpChange = "dontChangePros";
                break;
            case "Don't Change Cons":
                gpChange = "dontChangeCons";
                break;
        }
        setGpChangeDisplay(input);
    }
    public void setGpChangeDisplay(String input){ gpChangeDisplay = input; }


    public List<String> getGpChangeFactors(List<DocumentSnapshot> snapshotList, String behavior, String change){
        List<String> changeFactorList = new ArrayList<>();
        for (DocumentSnapshot snapshot : snapshotList) {
            //check if snapshot matches user option and contains the field
            if (snapshot.getString("behavior").equals(behavior) && snapshot.contains(change) && !snapshot.getString(change).equals("") && !(changeFactorList.contains(snapshot.getString(change)))) {
                String tempBarrier = snapshot.getString(change);
                //split the barrier string into an arraylist
                String[] tempBarrierList = tempBarrier.split("\\s*,\\s*");
                //add array items to list
                for(String string: tempBarrierList){
                    changeFactorList.add(string.trim());
                }
            }
        }
        return changeFactorList;
    }



    public Map<String, String> getGpBarrierMap(List<DocumentSnapshot> snapshotList, String behavior) {
        Map<String, String> barrierMap = new HashMap<>();
        for (DocumentSnapshot snapshot : snapshotList) {
            //check if snapshot matches user option and contains the field
            if (snapshot.getString("behavior").equals(behavior) && snapshot.contains("barrier") && !barrierMap.containsKey(snapshot.getString("barrier"))) {
                barrierMap.put(snapshot.getString("barrier"), snapshot.getString("barrierType"));
            }
        }
        return barrierMap;
    }

    public List<DocumentSnapshot> getGpSnapShotList() { return gpSnapShotList; }
    public List<String> getGpBehaviorList(){ return gpBehaviorList; }
    public String getGpBehavior(){return gpBehavior;}
    public String getGpChange(){return gpChange;}
    public String getGpChangeDisplay(){return gpChangeDisplay;}
}
