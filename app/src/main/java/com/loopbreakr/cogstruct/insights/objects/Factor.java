package com.loopbreakr.cogstruct.insights.objects;

public class Factor {
    private int color;
    private String Factor;
    private int numTimes;
    private String percentage;

    public Factor(int color, String factor, int numTimes, String percentage) {
        this.color = color;
        Factor = factor;
        this.numTimes = numTimes;
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getFactor() {
        return Factor;
    }

    public void setFactor(String factor) {
        Factor = factor;
    }

    public int getNumTimes() {
        return numTimes;
    }

    public void setNumTimes(int numTimes) {
        this.numTimes = numTimes;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
