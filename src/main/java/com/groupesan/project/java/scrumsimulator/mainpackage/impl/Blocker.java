package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import java.util.List;
import java.io.Serializable;
import java.util.UUID;

public class Blocker implements Serializable {
    private final UUID id;
    private String name;
    private double probability;
    private List<Solution> solutions;

    public Blocker() {
        this.id = UUID.randomUUID();
    }

    public Blocker(String name, double probability, List<Solution> solutions) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.probability = probability;
        this.solutions = solutions;
    }

    public UUID getId() { return id; }

    // Method to add a solution
    public void addSolution(Solution solution) {
        solutions.add(solution);
    }

    // Method to get solutions
    public List<Solution> getSolutions() {
        return solutions;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Blocker{" +
                "name='" + name + '\'' +
                ", probability=" + probability +
                ", solutions=" + solutions +
                '}';
    }
}
