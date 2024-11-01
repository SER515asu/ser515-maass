package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

public class SimulationPanel extends JPanel implements BaseComponent {

    private SimulationStateManager simulationStateManager;
    private BlockerManager blockerManager;
    private JButton startSimulationButton;
    private JButton stopSimulationButton;
    private JButton showBlockersButton;
    private JButton spikeActivityButton;

    /** Simulation Panel Initialization. */
    protected SimulationPanel(SimulationStateManager simulationStateManager) {
        this.simulationStateManager = simulationStateManager;
        this.blockerManager = new BlockerManager();
        this.setLayout(new GridBagLayout());
        this.init();
    }

    @Override
    public void init() {
        startSimulationButton = new JButton("Start Simulation");
        stopSimulationButton = new JButton("Stop Simulation");
        showBlockersButton = new JButton("Resolve Blockers");
        spikeActivityButton = new JButton("Spike Activity log");

        stopSimulationButton.setVisible(false);
        showBlockersButton.setVisible(false);
        spikeActivityButton.setVisible(false);

        spikeActivityButton.setBackground(Color.YELLOW);

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationStateManager.startSimulation();
                JOptionPane.showMessageDialog(null, "Simulation started!");
                updateButtonVisibility();
                updateBlockerButtonStatus();
                revalidate();
                repaint();
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (blockerManager.isBlockerListEmpty()) {
                    simulationStateManager.stopSimulation();
                    JOptionPane.showMessageDialog(null, "Simulation stopped!");
                    updateButtonVisibility();
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Resolve blockers first!");
                }
            }
        });

        showBlockersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blockerManager.showBlockersPanel();
                updateBlockerButtonStatus();
                revalidate();
                repaint();
            }
        });

        spikeActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpikePanel spikePanel = new SpikePanel();
                spikePanel.showSpikeLogFrame();
            }
        });

        add(startSimulationButton);
        add(stopSimulationButton);
        add(showBlockersButton);
        add(spikeActivityButton);
    }

    private void updateButtonVisibility() {
        boolean isRunning = simulationStateManager.isRunning();
        stopSimulationButton.setVisible(isRunning);
        startSimulationButton.setVisible(!isRunning);
        showBlockersButton.setVisible(isRunning);
        spikeActivityButton.setVisible(isRunning);
    }

    private void updateBlockerButtonStatus() {
        if (blockerManager.isBlockerListEmpty()) {
            showBlockersButton.setBackground(Color.GREEN);
            showBlockersButton.setText("No Blockers!");
        } else {
            showBlockersButton.setBackground(Color.RED);
            showBlockersButton.setText("Resolve Blockers");
        }
    }
}
