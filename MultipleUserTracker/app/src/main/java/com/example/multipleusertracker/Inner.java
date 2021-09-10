package com.example.multipleusertracker;

public class Inner {
    public String roll;
    public double latitude;
    public double longitude;
    public float distance;
    public Inner(){
    }

    public Inner(String roll, double latitude, double longitude, float distance) {
        this.roll = roll;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
