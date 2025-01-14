package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;

import java.io.PrintWriter;

public class UncheckCommand {
    private final Projects projects;
    private final PrintWriter printWriter;

    public UncheckCommand(Projects projects, PrintWriter printWriter) {
        this.projects = projects;
        this.printWriter = printWriter;
    }

    public void execute(String idString) {
        int id = Integer.parseInt(idString);
        if (!projects.markTaskByIdAsUnDone(id)) {
            printWriter.printf("Could not find a task with an ID of %d.", id);
            printWriter.println();
        }
    }
}
