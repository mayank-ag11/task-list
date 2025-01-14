package com.codurance.training.commands;

import java.io.PrintWriter;

public class HelpCommand {
    private final PrintWriter printWriter;
    private static final String[] COMMANDS = {
            "show",
            "add project <project name>",
            "add task <project name> <task description>",
            "check <task ID>",
            "uncheck <task ID>"
    };

    public HelpCommand(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void execute() {
        printWriter.println("Commands:");
        for(String command : COMMANDS) {
            printWriter.println("  " + command);
        }
        printWriter.println();
    }
}
