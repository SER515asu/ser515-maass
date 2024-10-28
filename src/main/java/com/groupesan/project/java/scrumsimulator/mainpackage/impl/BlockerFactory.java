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

    public List<Blocker> getAllBlockers() {
        return blockerStore.getBlockers();
    }

    public void addBlocker(Blocker blocker) {
        blockerStore.getBlockers().add(blocker);
        saveChanges();
    }

    public void saveChanges() {
        blockerStore.saveToJson();
    }
}

