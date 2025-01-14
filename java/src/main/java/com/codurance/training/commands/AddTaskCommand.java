package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;

import java.io.PrintWriter;

public class AddTaskCommand {
    private final Projects projects;
    private final PrintWriter printWriter;

    public AddTaskCommand(Projects projects, PrintWriter printWriter) {
        this.projects = projects;
        this.printWriter = printWriter;
    }

    public void execute(String projectName, String taskDescription) {
        if(!projects.addTaskToProjectWithName(projectName, new Task(projects.nextId(), taskDescription, false))) {
            printWriter.printf("Could not find a project with the name \"%s\".", projectName);
            printWriter.println();
        }
    }
}
