package com.loopbreakr.cogstruct.insights.objects;

import java.util.List;

public class Factor {
    private String factor;
    private int numTimes;
    private List<String> factorsList;


    public Factor(String factor, int numTimes) {
        this.factor = factor;
        this.numTimes = numTimes;
    }



    public String getFactor() { return factor; }
    public void setFactor(String trigger) { this.factor = factor; }


    public int getNumTimes() { return numTimes; }
    public void setNumTimes(int numTimes) { this.numTimes = numTimes; }
}
