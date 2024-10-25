package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Solution implements Serializable {
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

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProbabilityOfSuccess() {
        return successProb;
    }

    public void setProbabilityOfSuccess(double successProb) {
        this.successProb = successProb;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "name='" + name + '\'' +
                ", successProb=" + successProb +
                '}';
    }
}

