package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockerStore {
    private static final Logger LOGGER = Logger.getLogger(BlockerStore.class.getName());
    private static BlockerStore blockerStore;
    private List<SprintBlocker> sprintBlocker;
    private static final String READ_FILE_PATH = "blockers.json";
    private static final String FILE_PATH = "src/main/resources/blockers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BlockerStore() {
        this.sprintBlocker = new ArrayList<>();
    }

    public void loadFromJson() {
        sprintBlocker = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(READ_FILE_PATH)) {
            if (is != null) {
                sprintBlocker = objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, SprintBlocker.class));
            } else {
                sprintBlocker = new ArrayList<>();
                System.err.println("File not found: " + READ_FILE_PATH);
            }
        } catch (Exception e) {
            sprintBlocker = new ArrayList<>();
            LOGGER.log(Level.SEVERE, "Failed to load blockers from JSON", e);
        }
    }

    public void saveToJson() {
        try (OutputStream os = new FileOutputStream(FILE_PATH)) {
            objectMapper.writeValue(os, sprintBlocker);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save blockers to JSON", e);
        }
    }

    public static BlockerStore getInstance() {
        if (blockerStore == null) {
            blockerStore = new BlockerStore();
        }
        return blockerStore;
    }

    //returns blocker
    public List<SprintBlocker> getBlockers() {
        return sprintBlocker;
    }

    // set blocker
    public void setBlockers(List<SprintBlocker> sprintBlocker) {
        this.sprintBlocker = sprintBlocker;
    }
}

