package com.codurance.training.tasks;

import com.codurance.training.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Projects projects = new Projects();
    private final BufferedReader in;
    private final PrintWriter out;

    private final Map<String, Consumer<String>> commandHandlers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        initializeCommands();
    }

    private void initializeCommands() {
        commandHandlers.put("show", args -> {
            new ShowCommand(projects, out).execute();
        });
        commandHandlers.put("add", this::add);
        commandHandlers.put("check", args -> {
            new CheckCommand(projects, out).execute(args);
        });
        commandHandlers.put("uncheck", args -> {
            new UncheckCommand(projects, out).execute(args);
        });
        commandHandlers.put("help", args -> {
            new HelpCommand(out).execute();
        });
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        String args = commandRest.length > 1 ? commandRest[1] : "";

        Consumer<String> handler = commandHandlers.get(command);

        if(handler != null) {
            handler.accept(args);
        }
        else {
            error(command);
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            new AddProjectCommand(projects).execute(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            new AddTaskCommand(projects, out).execute(projectTask[0], projectTask[1]);
        }
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }
}
