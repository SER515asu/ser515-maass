package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockerManager {
    ArrayList<String> blockers;

    public BlockerManager() {
        blockers = new ArrayList<>();
        //TODO - Currently we're adding dummy lockers for the understanding of the functionality. These will be replaced by actual blockers in subsequent tasks.
        addBlocker("Blocker 1");
        addBlocker("Blocker 2");
        addBlocker("Blocker 3");
        addBlocker("Blocker 4");
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
