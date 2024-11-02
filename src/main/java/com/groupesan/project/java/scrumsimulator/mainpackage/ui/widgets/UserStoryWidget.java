package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    JLabel businessValue;

    private UserStory userStory;

    private MouseAdapter openEditDialog = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            EditUserStoryForm form = new EditUserStoryForm(userStory);
            form.setVisible(true);

            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {

                    userStory = form.getUserStoryObject();
                    init();
                }
            });
        }
    };

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public void init() {
        removeAll();

        id = new JLabel(userStory.getId().toString());
        points = new JLabel(Double.toString(userStory.getPointValue()));
        name = new JLabel(userStory.getName());
        desc = new JLabel(userStory.getDescription());
        SprintValue = new JLabel(userStory.getSprintValue());
        businessValue = new JLabel("Business Value: " + userStory.getBusinessValue());

        id.addMouseListener(openEditDialog);
        points.addMouseListener(openEditDialog);
        name.addMouseListener(openEditDialog);
        desc.addMouseListener(openEditDialog);
        SprintValue.addMouseListener(openEditDialog);
        businessValue.addMouseListener(openEditDialog);

        setLayout(new GridBagLayout());
        add(id, new CustomConstraints(0, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(points, new CustomConstraints(1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(name, new CustomConstraints(2, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(desc, new CustomConstraints(3, 1, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(SprintValue, new CustomConstraints(4, 1, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(businessValue, new CustomConstraints(5, 1, GridBagConstraints.WEST, 0.4, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }
}
