package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Blocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Main class Blocker UI
public class BlockerSimulationUI extends JFrame implements BaseComponent {
    private List<BlockerWidget> blockersWidget = new ArrayList<>();

    public BlockerSimulationUI() {
        BlockerStore.getInstance().loadFromJson();
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setTitle("Configure Blockers");
        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        // Header Panel
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.add(new JLabel("Name"), new CustomConstraints(-1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Probability"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        // Initialize Blocker Widgets
        for (Blocker blocker : BlockerStore.getInstance().getBlockers()) {
            blockersWidget.add(new BlockerWidget(blocker));
        }

        // Sub Panel
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 1; // 1 because of the presence of the header row
        for (BlockerWidget widget : blockersWidget) {
            subPanel.add(widget, new CustomConstraints(0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(new JScrollPane(subPanel), new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        // New Blocker Button
        JButton newBlockerButton = new JButton("New Blocker");
        newBlockerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: New Blocker Panel
            }
        });
        myJpanel.add(newBlockerButton, new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}
