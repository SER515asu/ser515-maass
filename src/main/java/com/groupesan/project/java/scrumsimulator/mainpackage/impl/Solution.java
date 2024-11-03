package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Solution {
    private final UUID id;
    private String name;
    private double successProb;

    public Solution() {
        this.id = UUID.randomUUID();
    }

    public Solution(String name, double successProb) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.successProb = successProb;
    }

    public Solution(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    // Method to get name
    public String getName() {
        return name;
    }

    // Method to set name
    public void setName(String name) {
        this.name = name;
    }

    // Method to get solution success probability
    public double getProbabilityOfSuccess() {
        return successProb;
    }

    // Method to set solution success probability
    public void setProbabilityOfSuccess(double successProb) {
        this.successProb = successProb;
    }

    @Override
    public String toString() {
        return  name+"-" + successProb;
    }
}

