package com.loopbreakr.cogstruct.thoughtjournal.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThoughtJournalObject {
    private String dateCreated;
    private String userId;
    private String location;
    private String time;
    private String people;
    private String situation;
    private String behavior;
    private String emotion;
    private String emotionDetail;
    private String thoughts;
    private String formName;
    private String thoughtString;
    private float emotionRating;
    private List<String> thoughtLogList = new ArrayList<>();

    public ThoughtJournalObject(String dateCreated, String userId, String location, String time, String people, String situation, String behavior, String emotion, float emotionRating, String emotionDetail, String thoughts) {
        this.formName = "Thought Journal";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.location = location;
        this.time = time;
        this.people = people;
        this.situation = situation;
        this.behavior = behavior;
        this.emotion = emotion;
        this.emotionRating = emotionRating;
        this.emotionDetail = emotionDetail;
        this.thoughts = thoughts;
    }

    public ThoughtJournalObject() {
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName){
        this.formName = formName;
    }

    public String getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPeople() {
        return people;
    }
    public void setPeople(String people) {
        this.people = people;
    }
    public String getSituation() {
        return situation;
    }
    public void setSituation(String situation) {
        this.situation = situation;
    }
    public String getBehavior() {
        return behavior;
    }
    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
    public String getEmotion() {
        return emotion;
    }
    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }
    public String getEmotionDetail() {
        return emotionDetail;
    }
    public void setEmotionDetail(String emotionDetail) {
        this.emotionDetail = emotionDetail;
    }
    public String getThoughts() {
        return thoughts;
    }
    public void setThoughtString(String thoughts) { this.thoughts = thoughts; }
    public float getEmotionRating() {
        return emotionRating;
    }
    public void setEmotionRating(float emotionRating) { this.emotionRating = emotionRating; }
    public String getThoughtStringList() { return thoughtString; }

    public List<String> getThoughtLogList() {
        thoughtLogList = new ArrayList<>(Arrays.asList(thoughts.split(",")));
        return thoughtLogList; }

    public void addToThoughtLogList(String thought){ thoughtLogList.add(thought); }
    public void updateThoughtString() { this.thoughtString = android.text.TextUtils.join(",", thoughtLogList); }
}
