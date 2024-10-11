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

    private JLabel idLabel;
    private JLabel pointsLabel;
    private JLabel nameLabel;
    private JLabel descLabel;
    private JLabel businessValueLabel;  // Updated to Business Value label

    // UserStory is non-transient; for serialization, additional handling may be required.
    private UserStory userStory;

    // Event listeners
    private ActionListener actionListener = e -> {};
    
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

        // Initialize labels and set text based on UserStory attributes
        idLabel = new JLabel(userStory.getId().toString());
        idLabel.addMouseListener(openEditDialog);

        pointsLabel = new JLabel(Double.toString(userStory.getPointValue()));
        pointsLabel.addMouseListener(openEditDialog);

        nameLabel = new JLabel(userStory.getName());
        nameLabel.addMouseListener(openEditDialog);

        descLabel = new JLabel(userStory.getDescription());
        descLabel.addMouseListener(openEditDialog);

        businessValueLabel = new JLabel("Business Value: " + userStory.getBusinessValue());  
        businessValueLabel.addMouseListener(openEditDialog);

        
        GridBagLayout myGridBagLayout = new GridBagLayout();
        setLayout(myGridBagLayout);

       
        add(
            idLabel,
            new CustomConstraints(
                    0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            pointsLabel,
            new CustomConstraints(
                    1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            nameLabel,
            new CustomConstraints(
                    2, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            descLabel,
            new CustomConstraints(
                    3, 0, GridBagConstraints.WEST, 0.5, 0.0, GridBagConstraints.HORIZONTAL));
        add(
            businessValueLabel,  
            new CustomConstraints(
                    4, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
    }
}
