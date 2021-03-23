package com.loopbreakr.cogstruct.proscons.objects;

public class ProsConsObject {
    private String formName;
    private String dateCreated;
    private String userId;
    private String behavior;
    private String changePros;
    private String dontChangePros;
    private String changeCons;
    private String dontChangeCons;

    public ProsConsObject() {
    }

    public ProsConsObject(String dateCreated, String userId, String behavior, String changePros, String dontChangePros, String changeCons, String dontChangeCons) {
        this.formName = "Pros and Cons";
        this.dateCreated = dateCreated;
        this.userId = userId;
        this.behavior = behavior;
        this.changePros = changePros;
        this.dontChangePros = dontChangePros;
        this.changeCons = changeCons;
        this.dontChangeCons = dontChangeCons;
    }

    public String getFormName() { return formName; }
    public void setFormName(String formName) { this.formName = formName; }
    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public String getChangePros() { return changePros; }
    public void setChangePros(String changePros) { this.changePros = changePros; }
    public String getDontChangePros() { return dontChangePros; }
    public void setDontChangePros(String dontChangePros) { this.dontChangePros = dontChangePros; }
    public String getChangeCons() { return changeCons; }
    public void setChangeCons(String changeCons) { this.changeCons = changeCons; }
    public String getDontChangeCons() { return dontChangeCons; }
    public void setDontChangeCons(String dontChangeCons) { this.dontChangeCons = dontChangeCons;
    }
}
