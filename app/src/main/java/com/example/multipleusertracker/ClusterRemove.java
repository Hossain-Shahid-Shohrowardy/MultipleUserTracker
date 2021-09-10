package com.example.multipleusertracker;

public class ClusterRemove {
    public String roll;
    public double stdlatitude;
    public double stdlongitude;
    public double endlatitude;
    public double endlongitude;
    public float distance;
   public ClusterRemove(){}

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public double getStdlatitude() {
        return stdlatitude;
    }

    public void setStdlatitude(double stdlatitude) {
        this.stdlatitude = stdlatitude;
    }

    public double getStdlongitude() {
        return stdlongitude;
    }

    public void setStdlongitude(double stdlongitude) {
        this.stdlongitude = stdlongitude;
    }

    public double getEndlatitude() {
        return endlatitude;
    }

    public void setEndlatitude(double endlatitude) {
        this.endlatitude = endlatitude;
    }

    public double getEndlongitude() {
        return endlongitude;
    }

    public void setEndlongitude(double endlongitude) {
        this.endlongitude = endlongitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public ClusterRemove(String roll, double stdlatitude, double stdlongitude, double endlatitude, double endlongitude, float distance) {
        this.roll = roll;
        this.stdlatitude = stdlatitude;
        this.stdlongitude = stdlongitude;
        this.endlatitude = endlatitude;
        this.endlongitude = endlongitude;
        this.distance = distance;
    }
}
