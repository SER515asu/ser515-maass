package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SprintBlocker {
    private final UUID id;
    private String name;
    private double probability;
    private List<Solution> solutions;

    public SprintBlocker() {

        this.id = UUID.randomUUID();
        this.solutions = new ArrayList<>();
    }

    public SprintBlocker(String name, double probability,List<Solution> solutions) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.probability = probability;
        this.solutions = solutions;
    }

    public UUID getId() { return id; }

    public void addSolution(Solution solution) {
        solutions.add(solution);
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }


    @Override
    public String toString() {
        return "SprintBlocker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", probability=" + probability +
                ", solutions=" + solutions +
                '}';

    }
}
