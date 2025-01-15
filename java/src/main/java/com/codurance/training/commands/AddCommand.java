package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;

import java.io.PrintWriter;

public class AddCommand {
    private final Projects projects;
    private final PrintWriter printWriter;

    public AddCommand(Projects projects, PrintWriter printWriter) {
        this.projects = projects;
        this.printWriter = printWriter;
    }

    public void execute(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            new AddProjectCommand(projects).execute(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            new AddTaskCommand(projects, printWriter).execute(projectTask[0], projectTask[1]);
        }
    }
}
