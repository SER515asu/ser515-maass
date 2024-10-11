package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class UserStoryFactory {

    private static UserStoryFactory userStoryFactory;

    // Singleton pattern to ensure only one instance of the factory
    public static UserStoryFactory getInstance() {
        if (userStoryFactory == null) {
            userStoryFactory = new UserStoryFactory();
        }
        return userStoryFactory;
    }

    // Private constructor to prevent instantiation
    private UserStoryFactory() {}

    /**
     * Creates a new UserStory with the provided parameters.
     * 
     * @param name the name of the user story
     * @param description the description of the user story
     * @param pointValue the point value of the user story (usually in Fibonacci sequence)
     * @param businessValue the business value for the user story (integer between 1 and 10)
     * @return a new instance of UserStory
     */
    public UserStory createNewUserStory(String name, String description, double pointValue,String sprintValue, int businessValue) {
            if (businessValue < 1 || businessValue > 10) {
            throw new IllegalArgumentException("Business Value must be between 1 and 10.");
        }

        UserStory newUS = new UserStory(name, description, pointValue, sprintValue, businessValue);
        return newUS;
    }
}
