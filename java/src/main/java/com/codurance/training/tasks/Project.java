package com.codurance.training.tasks;

class Project {
    private final Tasks tasks;
    private final String name;

    Project(String name) {
        this.name = name;
        this.tasks = new Tasks();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    boolean markTaskByIdAsDone(int id) {
        return tasks.markTaskByIdAsDone(id);
    }

    boolean markTaskByIdAsUnDone(int id) {
        return tasks.markTaskByIdAsUnDone(id);
    }

    String format() {
        return name + System.lineSeparator()
                + tasks.format("    ") + System.lineSeparator();
    }
}
