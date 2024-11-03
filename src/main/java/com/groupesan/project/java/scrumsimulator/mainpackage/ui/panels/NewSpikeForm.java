package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSpikeForm extends JFrame {

    private JTextField spikeTitleField;
    private JTextField spikeDescriptionField;
    private JButton submitButton;
    private JButton cancelButton;

    public NewSpikeForm(SpikePanel spikePanel) { 
        setTitle("Add New Spike");
        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints spikeform = new GridBagConstraints();
        spikeform.insets = new Insets(10, 10, 10, 10);
        spikeform.fill = GridBagConstraints.HORIZONTAL;
        spikeform.gridx = 0;
        spikeform.gridy = 0;
        add(new JLabel("Spike Title:"), spikeform);

        spikeform.gridx = 1;
        spikeTitleField = new JTextField(15);
        add(spikeTitleField, spikeform);

        spikeform.gridx = 0;
        spikeform.gridy = 1;
        add(new JLabel("Description:"), spikeform);

        spikeform.gridx = 1;
        spikeDescriptionField = new JTextField(15);
        add(spikeDescriptionField, spikeform);

        spikeform.gridx = 0;
        spikeform.gridy = 2;
        submitButton = new JButton("Submit");
        add(submitButton, spikeform);

        spikeform.gridx = 1;
        cancelButton = new JButton("Cancel");
        add(cancelButton, spikeform);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String spikeTitle = spikeTitleField.getText();
                String spikeDescription = spikeDescriptionField.getText();
                if (!spikeTitle.isEmpty() && !spikeDescription.isEmpty()) {
                    spikePanel.addSpike(spikeTitle, spikeDescription);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(NewSpikeForm.this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
}
