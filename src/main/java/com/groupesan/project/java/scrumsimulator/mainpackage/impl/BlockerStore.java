package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockerStore {
    private static BlockerStore blockerStore;

    public static BlockerStore getInstance() {
        if (blockerStore == null) {
            blockerStore = new BlockerStore();
        }
        return blockerStore;
    }

    private List<SprintBlocker> sprintBlockers;

    public BlockerStore() {
        sprintBlockers = new ArrayList<>();
    }

    private static final Logger LOGGER = Logger.getLogger(BlockerStore.class.getName());
    private static final String READ_FILE_PATH = "blockers.json";
    private static final String FILE_PATH = "src/main/resources/blockers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loadFromJson() {
        sprintBlockers = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(READ_FILE_PATH)) {
            if (is != null) {
                sprintBlockers = objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, SprintBlocker.class));
            } else {
                sprintBlockers = new ArrayList<>();
                System.err.println("File not found: " + READ_FILE_PATH);
            }
        } catch (Exception e) {
            sprintBlockers = new ArrayList<>();
            LOGGER.log(Level.SEVERE, "Failed to load blockers from JSON", e);
        }
    }

    public void saveToJson() {
        try (OutputStream os = new FileOutputStream(FILE_PATH)) {
            objectMapper.writeValue(os, sprintBlockers);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save blockers to JSON", e);
        }
    }

    //returns blocker
    public List<SprintBlocker> getBlockers() {
        return sprintBlockers;
    }

    // set blocker
    public void setBlockers(List<SprintBlocker> sprintBlockers) {
        this.sprintBlockers = sprintBlockers;
    }

}
