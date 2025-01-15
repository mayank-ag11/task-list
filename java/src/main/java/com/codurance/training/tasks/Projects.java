package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class Projects {
    private final LinkedHashMap<String, Project> projectByName = new LinkedHashMap<>();

    private long lastId = 0;

    public void addProject(String name) {
        this.projectByName.put(name, new Project(name));
    }

    public boolean addTaskToProjectWithName(String projectName, Task task) {
        Project project = this.projectByName.get(projectName);
        if (project == null) {
            return false;
        }
        project.addTask(task);
        return true;
    }

    public boolean markTaskByIdAsDone(int id) {
        for (Map.Entry<String, Project> project : this.projectByName.entrySet()) {
            if (project.getValue().markTaskByIdAsDone(id)) return true;
        }
        return false;
    }

    public boolean markTaskByIdAsUnDone(int id) {
        for (Map.Entry<String, Project> project : this.projectByName.entrySet()) {
            if (project.getValue().markTaskByIdAsUnDone(id)) return true;
        }
        return false;
    }

    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Project> project : this.projectByName.entrySet()) {
            stringBuilder.append(project.getValue().format());
        }

        return stringBuilder.toString();
    }

    public long nextId() {
        return ++lastId;
    }
}
