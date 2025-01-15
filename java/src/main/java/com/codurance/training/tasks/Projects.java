package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class Projects extends LinkedHashMap<String, Project> {

    private long lastId = 0;

    public void addProject(String name) {
        this.put(name, new Project(name));
    }

    public boolean addTaskToProjectWithName(String projectName, Task task) {
        Project project = this.get(projectName);
        if (project == null) {
            return false;
        }
        project.addTask(task);
        return true;
    }

    public boolean markTaskByIdAsDone(int id) {
        for (Map.Entry<String, Project> project : this.entrySet()) {
            if (project.getValue().markTaskByIdAsDone(id)) return true;
        }
        return false;
    }

    public boolean markTaskByIdAsUnDone(int id) {
        for (Map.Entry<String, Project> project : this.entrySet()) {
            if (project.getValue().markTaskByIdAsUnDone(id)) return true;
        }
        return false;
    }

    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Project> project : this.entrySet()) {
            stringBuilder.append(project.getValue().format());
        }

        return stringBuilder.toString();
    }

    public long nextId() {
        return ++lastId;
    }
}
