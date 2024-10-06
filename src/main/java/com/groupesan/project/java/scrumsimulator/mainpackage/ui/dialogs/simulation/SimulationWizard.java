package com.groupesan.project.java.scrumsimulator.mainpackage.ui.dialogs.simulation;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.Simulation;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.DataModel;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.Wizard;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.WizardHandler;

public class SimulationWizard extends Wizard<Simulation> {
    private DataModel<String> simulationName;
    private DataModel<Object> sprintCount;
    private DataModel<List<ScrumRole>> roles;
    private DataModel<List<Player>> users;
    private DataModel<String> sprintDuration;

    public SimulationWizard(WizardHandler<Simulation> handler) {
        super(handler);
        setTitle("New Simulation");
    }

    @Override
    protected void initDataModels() {
        this.simulationName = new DataModel<>("New Simulation");
        this.sprintCount = new DataModel<>(1);
        this.roles = new DataModel<>(new ArrayList<>());
        this.users = new DataModel<>(new ArrayList<>());
        this.sprintDuration = new DataModel<>("1");
    }

    @Override
    protected List<WizardPage> build() {
        return List.of(
                new GeneralPage(simulationName, sprintCount,sprintDuration),
                new RolesPage(roles),
                new ParticipantsPage(users, roles));
    }

    @Override
    protected Simulation process() {
        Simulation simulation =
                new Simulation(simulationName.getData(), null, (Integer) sprintCount.getData());
        for (Player player : users.getData()) {
            player.doRegister();
            simulation.addPlayer(player);
        }
        return simulation;
    }
}
