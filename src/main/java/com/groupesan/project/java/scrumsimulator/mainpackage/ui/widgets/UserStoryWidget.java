package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserStoryWidget extends JPanel implements BaseComponent {

    JLabel id;
    JLabel points;
    JLabel name;
    JLabel desc;
    JLabel SprintValue;

    JLabel businessValue;  // Updated to Business Value label

    // UserStory is non-transient; for serialization, additional handling may be required.
   private UserStory userStory;

    private MouseAdapter openEditDialog = 
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    EditUserStoryForm form = new EditUserStoryForm(userStory);
                    form.setVisible(true);

                    form.addWindowListener(
                            new java.awt.event.WindowAdapter() {
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    init();  // Refresh UI when form is closed
                                }
                            });
                }
            };

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public void init() {
        // Clear previous components
        removeAll();

        id = new JLabel(userStory.getId().toString());
        id.addMouseListener(openEditDialog);
        points = new JLabel(Double.toString(userStory.getPointValue()));
        points.addMouseListener(openEditDialog);
        name = new JLabel(userStory.getName());
        name.addMouseListener(openEditDialog);
        desc = new JLabel(userStory.getDescription());
        desc.addMouseListener(openEditDialog);
        SprintValue = new JLabel(userStory.getSprintValue());
        SprintValue.addMouseListener(openEditDialog);

        businessValue = new JLabel("Business Value: " + userStory.getBusinessValue());  
        businessValue.addMouseListener(openEditDialog);

        
        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

       
        add(
            id,
            new CustomConstraints(
                    0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            points,
            new CustomConstraints(
                    1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            name,
            new CustomConstraints(
                    2, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                desc,
                new CustomConstraints(
                        3, 1, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                SprintValue,
                new CustomConstraints(
                        4, 1, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL
                )
        );

        add(
            businessValue,  
            new CustomConstraints(
                    5, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));
    }
}
