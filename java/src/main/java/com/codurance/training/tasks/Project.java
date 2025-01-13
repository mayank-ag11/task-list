package com.codurance.training.tasks;

public class Project {
    private final Tasks tasks;
    private final String name;

    public Project(String name) {
        this.name = name;
        this.tasks = new Tasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean markTaskByIdAsDone(int id) {
        return tasks.markTaskByIdAsDone(id);
    }

    public boolean markTaskByIdAsUnDone(int id) {
        return tasks.markTaskByIdAsUnDone(id);
    }

    @Override
    public String toString() {
        return name + System.lineSeparator()
                + tasks.format("    ") + System.lineSeparator();
    }
}
