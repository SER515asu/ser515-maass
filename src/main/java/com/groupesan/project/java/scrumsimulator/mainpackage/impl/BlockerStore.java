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

    private List<Blocker> blocker;

    public BlockerStore() {
        blocker = new ArrayList<>();
    }

    private static final Logger LOGGER = Logger.getLogger(BlockerStore.class.getName());
    private static final String READ_FILE_PATH = "blockers.json";
    private static final String FILE_PATH = "src/main/resources/blockers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loadFromJson() {
        blocker = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(READ_FILE_PATH)) {
            if (is != null) {
                blocker = objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, Blocker.class));
            } else {
                blocker = new ArrayList<>();
                System.err.println("File not found: " + READ_FILE_PATH);
            }
        } catch (Exception e) {
            blocker = new ArrayList<>();
            LOGGER.log(Level.SEVERE, "Failed to load blockers from JSON", e);
        }
    }

    public void saveToJson() {
        try (OutputStream os = new FileOutputStream(FILE_PATH)) {
            objectMapper.writeValue(os, blocker);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save blockers to JSON", e);
        }
    }

    //returns blocker
    public List<Blocker> getBlockers() {
        return blocker;
    }

    // set blocker
    public void setBlockers(List<Blocker> blocker) {
        this.blocker = blocker;
    }

}

