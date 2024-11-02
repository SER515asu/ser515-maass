package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockerManager {
    ArrayList<String> blockers;
    private static BlockerManager blockerManager;

    public static BlockerManager getInstance() {
        if (blockerManager == null) {
            blockerManager = new BlockerManager();
        }
        return blockerManager;
    }

    public BlockerManager() {
        blockers = new ArrayList<>();
    }

    public void addBlocker(String blocker) {
        blockers.add(blocker);
    }

    public void resolveBlocker(String blocker) {
        blockers.remove(blocker);
    }

    public boolean isBlockerListEmpty(){
        if (blockers.size()>0){
            return false;
        }
        else{
            return true;
        }
    }

    public void showBlockersPanel() {
        JFrame blockersFrame = new JFrame("Click on checkbox to resolve blocker!");
        blockersFrame.setSize(400, 300);
        blockersFrame.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String blocker : blockers) {
            JCheckBox checkBox = new JCheckBox(blocker);

            checkBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (checkBox.isSelected()) {
                        resolveBlocker(blocker);
                        blockersFrame.dispose();
                        showBlockersPanel();
                    }
                }
            });
            panel.add(checkBox);
        }

        blockersFrame.add(panel);
        blockersFrame.setVisible(true);
    }
}
