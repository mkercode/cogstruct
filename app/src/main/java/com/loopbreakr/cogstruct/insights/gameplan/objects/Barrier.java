package com.loopbreakr.cogstruct.insights.gameplan.objects;

public class Barrier {
    private String barrier;
    private String barrierType;

    public Barrier(String barrier, String barrierType){
        this.barrier = barrier;
        this.barrierType = barrierType;
    }

    public String getBarrier() { return barrier; }
    public void setBarrier(String barrier) { this.barrier = barrier; }
    public String getBarrierType() { return barrierType; }
    public void setBarrierType(String barrierType) { this.barrierType = barrierType; }
}
