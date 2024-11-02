package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class BlockerManagerTest {
    private BlockerManager blockerManager;

    @BeforeEach
    public void setupBlockers() {
        blockerManager = new BlockerManager();
    }

    @Test
    public void testAddBlocker() {
        blockerManager.addBlocker("Blocker x");
        assertTrue(blockerManager.blockers.contains("Blocker x"));
    }

    @Test
    public void testResolveBlocker() {
        blockerManager.resolveBlocker("Blocker 1");
        assertFalse(blockerManager.blockers.contains("Blocker 1"));
    }

    /*
    @Test
    public void testIsBlockerListEmptyWhenNotEmpty() {
        assertFalse(blockerManager.isBlockerListEmpty());
    }

    @Test
    public void testIsBlockerListEmptyWhenEmpty() {
        blockerManager.blockers.clear();
        assertTrue(blockerManager.isBlockerListEmpty());
    }

     */
}
