package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

public class SimulationPanel extends JPanel implements BaseComponent {

    private SimulationStateManager simulationStateManager;
    private JButton startSimulationButton;
    private JButton stopSimulationButton;
    private JButton showBlockersButton;
    private JButton resolveBlockerButton;
    private JButton spikeActivityButton;
    private static final SecureRandom secRandom = new SecureRandom();

    /** Simulation Panel Initialization. */
    protected SimulationPanel(SimulationStateManager simulationStateManager) {
        this.simulationStateManager = simulationStateManager;
        this.setLayout(new GridBagLayout());
        this.init();
    }

    private void populateBlockers(){
        double randomNumber = getNextRandomDouble();
        BlockerStore blockerStore = BlockerStore.getInstance();
        blockerStore.loadFromJson();
        List<SprintBlocker> blockers = blockerStore.getBlockers();
        BlockerManager blockerManager = BlockerManager.getInstance();
        for(SprintBlocker blocker: blockers){
            if(blocker.getProbability() > randomNumber){
                blockerManager.addBlocker(blocker.getName());
            }
        }
    }

    private static double getNextRandomDouble() {
        return secRandom.nextDouble();
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
        BlockerManager blockerManager = BlockerManager.getInstance();

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
                populateBlockers();
                simulationStateManager.startSimulation();
                JOptionPane.showMessageDialog(null, "Simulation started!");
                updateButtonVisibility();
                spikeActivityButton.setVisible(true);
                showBlockersButton.setVisible(true);
                if(blockerManager.isBlockerListEmpty()==false)
                {
                    showBlockersButton.setBackground(Color.RED);
                    showBlockersButton.setText("Resolve Blockers!");}
                else{
                    showBlockersButton.setBackground(Color.GREEN);
                    showBlockersButton.setText("No Blockers!");
                    revalidate();
                    repaint();
                }
                revalidate();
                repaint();
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(blockerManager.isBlockerListEmpty()==true){
                    simulationStateManager.stopSimulation();
                    JOptionPane.showMessageDialog(null, "Simulation stopped!");
                    showBlockersButton.setVisible(false);
                    showBlockersButton.setBackground(Color.GREEN);
                    showBlockersButton.setText("No Blockers!");
                    updateButtonVisibility();
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
                if(blockerManager.isBlockerListEmpty()==true){
                    showBlockersButton.setBackground(Color.GREEN);
                    showBlockersButton.setText("No Blockers!");
                }
                else{
                    showBlockersButton.setBackground(Color.RED);}
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
        if (simulationStateManager.isRunning()) {
            stopSimulationButton.setVisible(true);
            startSimulationButton.setVisible(false);
            showBlockersButton.setVisible(true);
        } else {
            stopSimulationButton.setVisible(false);
            startSimulationButton.setVisible(true);
        }
    }
}
