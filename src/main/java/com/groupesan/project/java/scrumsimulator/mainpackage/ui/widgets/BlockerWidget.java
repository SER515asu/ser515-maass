package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockerWidget extends JPanel implements BaseComponent {

    JLabel name;
    JLabel probability;

   private Blocker blocker;

    private MouseAdapter openEditDialog =
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //TODO: Open panel for edit blocker
                }
            };

    public BlockerWidget(Blocker blocker) {
        this.blocker = blocker;
        this.init();
    }

    public void init() {
        // Clear previous components
        removeAll();

        probability = new JLabel(String.valueOf(blocker.getProbability()));
        probability.addMouseListener(openEditDialog);
        name = new JLabel(blocker.getName());
        name.addMouseListener(openEditDialog);
        
        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

       
        add(
            name,
            new CustomConstraints(
                    0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            probability,
            new CustomConstraints(
                    1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
    }
}
