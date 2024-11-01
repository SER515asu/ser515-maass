package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
    private DefaultTableModel spikeTableModel;

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

     //   GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        this.add(startSimulationButton, gbc);
//
//        gbc.gridx = 1;
//        this.add(stopSimulationButton, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        this.add(showBlockersButton, gbc);
//
//        gbc.gridx = 1;
        spikeActivityButton.setBackground(Color.YELLOW);
//        this.add(spikeActivityButton, gbc);

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
                showSpikeLogPanel();
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

    private void showSpikeLogPanel() {
        JPanel spikeLogPanel = new JPanel(new BorderLayout());

        String[] columns = {"S.No", "Spike Title", "Reason/Desc", "Resolved"};

        spikeTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 3 ? Boolean.class : String.class;
            }
        };

        JTable spikeTable = new JTable(spikeTableModel);

        spikeTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        spikeTable.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(value != null && (Boolean) value);
                return checkBox;
            }
        });

        spikeTable.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            if (col == 3 && (Boolean) spikeTableModel.getValueAt(row, col)) {
                spikeTableModel.removeRow(row);
            }
        });

        JScrollPane scrollPane = new JScrollPane(spikeTable);

        JButton addNewSpikeButton = new JButton("Add Spike");
        addNewSpikeButton.addActionListener(e -> {
            // Implement logic to add a new spike
        });

        spikeLogPanel.add(scrollPane, BorderLayout.CENTER);
        spikeLogPanel.add(addNewSpikeButton, BorderLayout.SOUTH);

        JFrame spikeLogFrame = new JFrame("Spike Activity Log");
        spikeLogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        spikeLogFrame.setSize(600, 400);
        spikeLogFrame.add(spikeLogPanel);
        spikeLogFrame.setVisible(true);
    }
}
