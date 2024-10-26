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

    /** Simulation Panel Initialization. */
    protected SimulationPanel(SimulationStateManager simulationStateManager) {
        this.simulationStateManager = simulationStateManager;
        this.init();
    }

    @Override
    public void init() {
        startSimulationButton = new JButton("Start Simulation");
        stopSimulationButton = new JButton("Stop Simulation");
        showBlockersButton = new JButton("Resolve Blockers");
        resolveBlockerButton = new JButton("Resolve Blockers First!");

        stopSimulationButton.setVisible(false);
        showBlockersButton.setVisible(false);
        BlockerManager blockerManager = new BlockerManager();

        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationStateManager.startSimulation();
                JOptionPane.showMessageDialog(null, "Simulation started!");
                updateButtonVisibility();
                showBlockersButton.setVisible(true);
                showBlockersButton.setBackground(Color.RED);
                revalidate();
                repaint();
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(blockerManager.isBlockerListEmpty()){
                    simulationStateManager.stopSimulation();
                    JOptionPane.showMessageDialog(null, "Simulation stopped!");
                    updateButtonVisibility();
                    showBlockersButton.setVisible(false);
                    revalidate();
                    repaint();
                }
                else {
                    resolveBlockerButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            simulationStateManager.startSimulation();
                            JOptionPane.showMessageDialog(null, "Resolve blockers first!");
                        }
                    });
                }
            }
        });

        showBlockersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blockerManager.showBlockersPanel();
            }
        });
        add(startSimulationButton);
        add(stopSimulationButton);
        add(showBlockersButton);

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
