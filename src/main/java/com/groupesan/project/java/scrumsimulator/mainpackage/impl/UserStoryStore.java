package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class UserStoryStore {

    private static UserStoryStore userStoryStore;

    /**
     * returns the shared instance of the UserStoryStore which contains all user stories in the
     * system.
     *
     * @return
     */
    public static UserStoryStore getInstance() {
        if (userStoryStore == null) {
            userStoryStore = new UserStoryStore();
        }
        return userStoryStore;
    }

    private List<UserStory> userStories;

    private UserStoryStore() {
        userStories = new ArrayList<>();
    }

    // Add a setter for the list
    public void setUserStories(List<UserStory> userStories) {
        this.userStories = userStories;
    }

    public void addUserStory(UserStory userStory) {
        userStories.add(userStory);
    }

    public void removeUserStory(String storyName){
        List<UserStory> userStories = userStoryStore.getUserStories();
        UserStory storyToDelete = userStories.stream()
                .filter(story -> story.getName().equals(storyName))
                .findFirst()
                .orElse(null);
        if (storyToDelete != null) {
            userStories.remove(storyToDelete);
            userStoryStore.setUserStories(userStories);
        }
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }
}
