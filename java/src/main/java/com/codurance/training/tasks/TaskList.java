package com.codurance.training.tasks;

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

    private long lastId = 0;

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
        commandHandlers.put("show", args -> show());
        commandHandlers.put("add", this::add);
        commandHandlers.put("check", this::check);
        commandHandlers.put("uncheck", this::uncheck);
        commandHandlers.put("help", args -> help());
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

    private void show() {
        out.print(projects);
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        projects.addProject(name);
    }

    private void addTask(String project, String description) {
        if(!projects.addTaskToProjectWithName(project, new Task(nextId(), description, false))) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
        }
    }

    private void check(String idString) {
        int id = Integer.parseInt(idString);
        if (!projects.markTaskByIdAsDone(id)) {
            noTaskForTheIdError(id);
        }
    }

    private void uncheck(String idString) {
        int id = Integer.parseInt(idString);
        if (!projects.markTaskByIdAsUnDone(id)) {
            noTaskForTheIdError(id);
        }
    }

    private void noTaskForTheIdError(int id) {
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    private long nextId() {
        return ++lastId;
    }
}
