package com.loopbreakr.cogstruct.insights.behavioralinspection.objects;

@SuppressWarnings("unused")
public class Factor {
    private int factorColor;
    private String Factor;
    private int numTimes;
    private String percentage;
    private String factorContext;

    public Factor(int factorColor, String factor, int numTimes, String percentage, String factorContext) {
        this.factorColor = factorColor;
        Factor = factor;
        this.numTimes = numTimes;
        this.percentage = percentage;
        this.factorContext = factorContext;
    }

    public int getFactorColor() {
        return factorColor;
    }

    public void setFactorColor(int factorColor) {
        this.factorColor = factorColor;
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

    public String getFactorContext() {
        return factorContext;
    }

    public void setFactorContext(String factorContext) {
        this.factorContext = factorContext;
    }
}
