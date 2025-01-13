package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;

public class Projects extends LinkedHashMap<String, Tasks> {

    public boolean addTaskToProjectWithName(String project, Task task) {
        Tasks projectTasks = this.get(project);
        if (projectTasks == null) {
            return false;
        }
        projectTasks.add(task);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Tasks> project : this.entrySet()) {
            stringBuilder.append(project.getKey()).append(System.lineSeparator());
            stringBuilder.append(project.getValue().format("    ")).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
