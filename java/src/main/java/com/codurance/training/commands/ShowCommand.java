package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;

import java.io.PrintWriter;

public class ShowCommand {
    private final Projects projects;
    private final PrintWriter printWriter;

    public ShowCommand(Projects projects, PrintWriter printWriter) {
        this.projects = projects;
        this.printWriter = printWriter;
    }

    public void execute() {
        printWriter.print(projects);
    }
}
