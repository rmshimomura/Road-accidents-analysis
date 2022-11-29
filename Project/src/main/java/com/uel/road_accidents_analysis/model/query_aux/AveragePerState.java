package com.uel.road_accidents_analysis.model.query_aux;

public class AveragePerState {

    private String uf;
    private double icpAverage;
    private double icmAverage;
    private double iccAverage;

    public AveragePerState() {

    }

    public AveragePerState(String uf, double icpAverage, double icmAverage, double iccAverage) {
        this.uf = uf;
        this.icpAverage = icpAverage;
        this.icmAverage = icmAverage;
        this.iccAverage = iccAverage;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public double getIcpAverage() {
        return icpAverage;
    }

    public void setIcpAverage(double icpAverage) {
        this.icpAverage = icpAverage;
    }

    public double getIcmAverage() {
        return icmAverage;
    }

    public void setIcmAverage(double icmAverage) {
        this.icmAverage = icmAverage;
    }

    public double getIccAverage() {
        return iccAverage;
    }

    public void setIccAverage(double iccAverage) {
        this.iccAverage = iccAverage;
    }
}
