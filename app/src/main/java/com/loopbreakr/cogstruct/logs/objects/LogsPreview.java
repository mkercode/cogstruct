package com.loopbreakr.cogstruct.logs.objects;

public class LogsPreview {
    private String formName;
    private String dateCreated;
    private String userId;
    private String thoughts;
    private String emotion;
    private float emotionRating;
    private String location;
    private String emotionDetail;
    private String time;
    private String behavior;
    private String people;
    private String situation;

    public LogsPreview(String formName, String dateCreated, String userId) {
        this.formName = formName;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    //required empty constructor for firebase
    public LogsPreview() { }
    public CharSequence getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }

    /**
     * getters and setters required by firebase
     **/

    public String getThoughts() { return thoughts; }
    public void setThoughts(String thoughts) { this.thoughts = thoughts;}
    public String getEmotion() { return emotion; }
    public void setEmotion(String emotion) { this.emotion = emotion; }
    public float getEmotionRating() { return emotionRating; }
    public void setEmotionRating(float emotionRating) { this.emotionRating = emotionRating; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getEmotionDetail() { return emotionDetail; }
    public void setEmotionDetail(String emotionDetail) { this.emotionDetail = emotionDetail; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public String getPeople() { return people; }
    public void setPeople(String people) { this.people = people; }
    public String getSituation() { return situation; }
    public void setSituation(String situation) { this.situation = situation; }
}

