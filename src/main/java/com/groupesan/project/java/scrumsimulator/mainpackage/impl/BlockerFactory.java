package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class BlockerFactory {
    private static BlockerFactory blockerFactory;

    public static BlockerFactory getInstance() {
        if(blockerFactory == null) {
            blockerFactory = new BlockerFactory();
        }
        return blockerFactory;
    }

    private BlockerFactory() {}
}