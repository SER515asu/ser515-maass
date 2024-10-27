package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel implements BaseComponent {

    private SimulationStateManager simulationStateManager;
    private JButton startSimulationButton;
    private JButton stopSimulationButton;
    private JButton showBlockersButton;
    private JButton resolveBlockerButton;
    private JButton spikeActivityButton;

    /** Simulation Panel Initialization. */
    protected SimulationPanel(SimulationStateManager simulationStateManager) {
        this.simulationStateManager = simulationStateManager;
        this.setLayout(new GridBagLayout()); 
        this.init();
    }

    @Override
    public void init() {
        startSimulationButton = new JButton("Start Simulation");
        stopSimulationButton = new JButton("Stop Simulation");
        showBlockersButton = new JButton("Resolve Blockers");
        resolveBlockerButton = new JButton("Resolve Blockers First!");
        spikeActivityButton = new JButton("Spike Activity log");

        stopSimulationButton.setVisible(false);
        showBlockersButton.setVisible(false);
        spikeActivityButton.setVisible(false);
        BlockerManager blockerManager = new BlockerManager();

        GridBagConstraints startsimulation = new GridBagConstraints();
        startsimulation.gridx = 0; 
        startsimulation.gridy = 0;
        startsimulation.insets = new Insets(10, 10, 10, 10);
        this.add(startSimulationButton, startsimulation);

        GridBagConstraints stopsimulation = new GridBagConstraints();
        stopsimulation.gridx = 1; 
        stopsimulation.gridy = 0;
        stopsimulation.insets = new Insets(10, 10, 10, 10);
        this.add(stopSimulationButton, stopsimulation);

        GridBagConstraints ShowBlockers = new GridBagConstraints();
        ShowBlockers.gridx = 2; 
        ShowBlockers.gridy = 0;
        ShowBlockers.insets = new Insets(10, 10, 10, 10);
        showBlockersButton.setBackground(Color.RED);
        this.add(showBlockersButton, ShowBlockers);

        GridBagConstraints spikeactivity = new GridBagConstraints();
        spikeactivity.gridx = 2; 
        spikeactivity.gridy = 1;
        spikeactivity.insets = new Insets(10, 10, 10, 10);
        spikeactivity.fill = GridBagConstraints.HORIZONTAL;
        spikeActivityButton.setBackground(Color.YELLOW);
        this.add(spikeActivityButton, spikeactivity);

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationStateManager.startSimulation();
                JOptionPane.showMessageDialog(null, "Simulation started!");
                updateButtonVisibility();
                spikeActivityButton.setVisible(true);
                showBlockersButton.setVisible(true);
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
                    showBlockersButton.setVisible(false);
                    spikeActivityButton.setVisible(false);
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
            }
        });
    }

    private void updateButtonVisibility() {
        if (simulationStateManager.isRunning()) {
            stopSimulationButton.setVisible(true);
            startSimulationButton.setVisible(false);
        } else {
            stopSimulationButton.setVisible(false);
            startSimulationButton.setVisible(true);
        }
    }
}
