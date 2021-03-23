package com.loopbreakr.cogstruct.howdigethere.objects;

public class HIGHObject {

    private String formName;
    private String dateCreated;
    private String userId;
    private String behavior;
    private String triggerEvent;
    private String emotion;
    private float emotionRating;
    private String thoughts;
    private String vulnerabilities;
    private String reliefs;
    private String consequences;
    private String solutions;
    private String repairs;

    public HIGHObject() { }

    public HIGHObject(String dateCreated, String userId, String behavior, String triggerEvent, String emotion, float emotionRating, String thoughts, String vulnerabilities, String reliefs, String consequences, String solutions, String repairs) {
        this.formName = "How'd I Get Here?";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.behavior = behavior;
        this.triggerEvent = triggerEvent;
        this.emotion = emotion;
        this.emotionRating = emotionRating;
        this.thoughts = thoughts;
        this.vulnerabilities = vulnerabilities;
        this.reliefs = reliefs;
        this.consequences = consequences;
        this.solutions = solutions;
        this.repairs = repairs;
    }

    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public String getTriggerEvent() { return triggerEvent; }
    public void setTriggerEvent(String triggerEvent) { this.triggerEvent = triggerEvent; }
    public String getEmotion() { return emotion; }
    public void setEmotion(String emotion) { this.emotion = emotion; }
    public float getEmotionRating() { return emotionRating; }
    public void setEmotionRating(float emotionRating) { this.emotionRating = emotionRating; }
    public String getThoughts() { return thoughts; }
    public void setThoughts(String thoughts) { this.thoughts = thoughts; }
    public String getVulnerabilities() { return vulnerabilities; }
    public void setVulnerabilities(String vulnerabilities) { this.vulnerabilities = vulnerabilities; }
    public String getReliefs() { return reliefs; }
    public void setReliefs(String reliefs) { this.reliefs = reliefs; }
    public String getConsequences() { return consequences; }
    public void setConsequences(String consequences) { this.consequences = consequences; }
    public String getSolutions() { return solutions; }
    public void setSolutions(String solutions) { this.solutions = solutions; }
    public String getRepairs() { return repairs; }
    public void setRepairs(String repairs) { this.repairs = repairs; }
}
