package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.BlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Solution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;


public class BlockerWidget extends JPanel implements BaseComponent {

    JLabel name;
    JLabel probability;
    JLabel solutions;
   private SprintBlocker sprintBlocker;

    private MouseAdapter openEditDialog =
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openEditPanel();
                }
            };

    public BlockerWidget(SprintBlocker sprintBlocker) {
        this.sprintBlocker = sprintBlocker;
        this.init();
    }


    public void init() {
        removeAll();
 
        probability = new JLabel(String.valueOf(sprintBlocker.getProbability()));
        probability.addMouseListener(openEditDialog);
        name = new JLabel(sprintBlocker.getName());
        name.addMouseListener(openEditDialog);
        solutions = new JLabel(sprintBlocker.getSolutions().toString());
        solutions.addMouseListener(openEditDialog);      
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
        add(
            solutions,
            new CustomConstraints(
                    2, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        
            
    }
    private void openEditPanel() {
        JDialog eDialog = new JDialog();
        eDialog.setTitle("Edit Blocker");
        eDialog.setLayout(new GridBagLayout());

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(sprintBlocker.getName());
        eDialog.add(nameLabel, new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        eDialog.add(nameField, new CustomConstraints(1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel probLabel = new JLabel("Probability:");
        JTextField probField = new JTextField(String.valueOf(sprintBlocker.getProbability()));
        eDialog.add(probLabel, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        eDialog.add(probField, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel solLabel = new JLabel("Solutions-Probability of Success:");
        StringBuilder st = new StringBuilder();
        for (Solution solution : sprintBlocker.getSolutions()) {
            st.append(solution.getName()).append("-").append(solution.getProbabilityOfSuccess()).append(",");
        }
        JTextField solField = new JTextField(sprintBlocker.getSolutions().toString());
        eDialog.add(solLabel, new CustomConstraints(0, 2, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        eDialog.add(solField, new CustomConstraints(1, 2, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        JButton savebutton = new JButton("Save");
        savebutton.addActionListener(e -> {
            sprintBlocker.setName(nameField.getText());
            sprintBlocker.setProbability(Double.parseDouble(probField.getText()));
            Solution solution = new Solution(solField.getText());
            List<Solution> solutions = new ArrayList<>();
            String[] arr = solField.getText().split(",");
            for (String s : arr) {
                s = s.replace("[","").replace("]","").trim();
                String[] parts = s.split("-");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double probabilityOfSuccess = Double.parseDouble(parts[1].trim());
                    solutions.add(new Solution(name, probabilityOfSuccess));
                }
            }
            sprintBlocker.setSolutions(solutions);

            BlockerStore.getInstance().saveToJson();

            init();
            revalidate();
            repaint();
            eDialog.dispose();
        });
        eDialog.add(savebutton, new CustomConstraints(1, 3, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        eDialog.pack();
        eDialog.setVisible(true);
    }
}
