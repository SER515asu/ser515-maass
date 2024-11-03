package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

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

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.add(new JLabel("Name"), new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Probability"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Solutions"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));

        for (SprintBlocker sprintBlocker : BlockerStore.getInstance().getBlockers()) {
            blockersWidget.add(new BlockerWidget(sprintBlocker));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 1;
        for (BlockerWidget widget : blockersWidget) {
            subPanel.add(widget, new CustomConstraints(0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(new JScrollPane(subPanel), new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newBlockerButton = new JButton("New Blocker");
        newBlockerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel newBlockerPanel = new JPanel();
                newBlockerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                newBlockerPanel.setLayout(new GridBagLayout());

                JLabel nameLabel = new JLabel("Name");
                newBlockerPanel.add(nameLabel, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
                JTextField nameField = new JTextField(20);
                newBlockerPanel.add(nameField, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
                
                JLabel probLabel = new JLabel("Probability");
                newBlockerPanel.add(probLabel, new CustomConstraints(0, 2, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
                JTextField probField = new JTextField(20);
                newBlockerPanel.add(probField, new CustomConstraints(1, 2, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
                
                JLabel solLabel = new JLabel("Solutions - Probability of Success");
                newBlockerPanel.add(solLabel, new CustomConstraints(0, 3, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
                JTextField solField = new JTextField();
                newBlockerPanel.add(solField, new CustomConstraints(1, 3, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
                
                JFrame newBlockerFrame = new JFrame("New Blocker");
                newBlockerFrame.setSize(600, 600);
                newBlockerFrame.add(newBlockerPanel);
                newBlockerFrame.pack();
                newBlockerFrame.setVisible(true);

                JButton confirmButton = new JButton("Confirm");
                newBlockerPanel.add(confirmButton, new CustomConstraints(1, 6, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newBlockerFrame.dispose();
                        String name = nameField.getText();
                        double probability = Double.parseDouble(probField.getText());
                        String[] solutionsArray = solField.getText().split(",");
                        List<Solution> solutions = new ArrayList<>();
                        for (String s: solutionsArray) {
                            String[] parts = s.split("-");
                            String solutionName = parts[0];
                            double probabilityOfSuccess = Double.parseDouble(parts[1]);
                            solutions.add(new Solution(solutionName, probabilityOfSuccess));
                        }

                        SprintBlocker blocker = new SprintBlocker(name, probability,solutions);
                        
                        BlockerStore.getInstance().addBlocker(blocker);

                        BlockerWidget widget = new BlockerWidget(blocker);
                        blockersWidget.add(widget);
                        subPanel.add(widget, new CustomConstraints(0, blockersWidget.size(), GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
                        subPanel.revalidate();
                        subPanel.repaint();
                        
                    }
                });
            }
        });
        myJpanel.add(newBlockerButton, new CustomConstraints(0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}
