package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class BlockerFactory {
    private BlockerStore blockerStore;

    public BlockerFactory() {
        blockerStore = BlockerStore.getInstance();
        if (blockerStore.getBlockers() == null) {
            blockerStore.setBlockers(new ArrayList<>());
        }
    }

    public List<SprintBlocker> getAllBlockers() {
        return blockerStore.getBlockers();
    }

    public void addBlocker(SprintBlocker sprintBlocker) {
        blockerStore.getBlockers().add(sprintBlocker);
        saveChanges();
    }

    public void saveChanges() {
        blockerStore.saveToJson();
    }
}

