package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserStoryListPane extends JFrame implements BaseComponent {
    public UserStoryListPane() {
        this.init();
    }

    private List<UserStoryWidget> widgets = new ArrayList<>();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("User Story list");
        setSize(600, 400);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.add(new JLabel("ID"), new CustomConstraints(-1, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Points"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Name"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("Description"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("SprintValue"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        headerPanel.add(new JLabel("BusinessValue"), new CustomConstraints(GridBagConstraints.RELATIVE, 1, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));

        JPanel subPanel = new JPanel();
      //  subPanel.add(headerPanel, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            widgets.add(new UserStoryWidget(userStory));
        }

//        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 1; //1 because of presence of header row
        for (UserStoryWidget widget : widgets) {

            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newSprintButton = new JButton("New User Story");
        newSprintButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewUserStoryForm form = new NewUserStoryForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                        UserStory userStory = form.getUserStoryObject();
                                        if (userStory != null) {
                                            UserStoryStore.getInstance().addUserStory(userStory);
                                            UserStoryWidget widget = new UserStoryWidget(userStory);
                                            widgets.add(widget);
                                            int index = widgets.size()-1;
                                            subPanel.add( widget, new CustomConstraints(0, index, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
                                            subPanel.revalidate();
                                            subPanel.repaint();
                                        }
                                    }
                                });
                    }
                });
        myJpanel.add(
                newSprintButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }
}
