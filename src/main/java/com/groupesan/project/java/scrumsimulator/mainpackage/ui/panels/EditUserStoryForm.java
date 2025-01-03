package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

public class EditUserStoryForm extends JFrame implements BaseComponent {

    Double[] pointsList = {1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
    String[] spritsList = {"Sprint1","Sprint2","Sprint3"};
    Integer[] businessValueList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  

    public EditUserStoryForm(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    private UserStory userStory;

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<String> sprintsCombo = new JComboBox<>(spritsList);
    private JComboBox<Integer> businessValueCombo = new JComboBox<>(businessValueList); 
    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit User Story " + userStory.getId().toString());
        setSize(400, 300);

        nameField = new JTextField(userStory.getName());
        descArea = new JTextArea(userStory.getDescription());
        pointsCombo = new JComboBox<>(pointsList);
        pointsCombo.setSelectedItem(userStory.getPointValue());
        sprintsCombo = new JComboBox<>(spritsList);
        sprintsCombo.setSelectedItem(userStory.getSprintValue());
        businessValueCombo.setSelectedItem(userStory.getBusinessValue());
      
        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel pointsLabel = new JLabel("Points:");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                pointsCombo,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel sprintsLabel = new JLabel("Sprint:");
        myJpanel.add(
                sprintsLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                sprintsCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        
        JLabel businessValueLabel = new JLabel("Business Value:");
        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                businessValueCombo,
                new CustomConstraints(
                        1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String description = descArea.getText();
                        Double points = (Double) pointsCombo.getSelectedItem();
                        String sprint = (String) sprintsCombo.getSelectedItem();
                        Integer businessValue = (Integer) businessValueCombo.getSelectedItem();
                        
                        userStory.setName(name);
                        userStory.setDescription(description);
                        userStory.setPointValue(points);
                        userStory.setSprintValue(sprint);
                        userStory.setBusinessValue(businessValue);
                        dispose();
                    }
                });

        JButton deleteStoryButton = new JButton("Delete Story");

        deleteStoryButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        UserStoryStore.getInstance().removeUserStory(name);
                        UserStoryListPane form = new UserStoryListPane();
                        form.setVisible(true);
                        dispose();
                    }
                });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 5, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 5, GridBagConstraints.WEST, GridBagConstraints.NONE));

        myJpanel.add(
                deleteStoryButton,
                new CustomConstraints(2, 5, GridBagConstraints.EAST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public UserStory getUserStoryObject() {
        return userStory;
    }
}
