package com.example.multipleusertracker;

public class Head {
public String clusterhead;
public String member;
    public Head(){

    }

    public Head(String clusterhead, String member) {
        this.clusterhead = clusterhead;
        this.member = member;
    }

    public String getClusterhead() {
        return clusterhead;
    }

    public void setClusterhead(String clusterhead) {
        this.clusterhead = clusterhead;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
