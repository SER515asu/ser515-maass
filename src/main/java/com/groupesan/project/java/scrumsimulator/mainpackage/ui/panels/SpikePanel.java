package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpikePanel extends JPanel {

    private DefaultTableModel spikeTableModel;

    public SpikePanel() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
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
        addNewSpikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to add a new spike
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(addNewSpikeButton, BorderLayout.SOUTH);
    }

    public void showSpikeLogFrame() {
        JFrame spikeLogFrame = new JFrame("Spike Activity Log");
        spikeLogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        spikeLogFrame.setSize(600, 400);
        spikeLogFrame.add(this);
        spikeLogFrame.setVisible(true);
    }
}
