package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;

public class AddProjectCommand {
    private final Projects projects;

    public AddProjectCommand(Projects projects) {
        this.projects = projects;
    }

    public void execute(String name) {
        projects.addProject(name);
    }
}
