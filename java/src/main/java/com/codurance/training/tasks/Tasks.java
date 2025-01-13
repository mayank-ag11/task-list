package com.codurance.training.tasks;

import java.util.ArrayList;

public class Tasks extends ArrayList<Task> {

    public boolean markTaskByIdAsDone(int id) {
        for (Task task : this) {
            if (task.hasId(id)) {
                task.markAsDone();
                return true;
            }
        }
        return false;
    }

    public boolean markTaskByIdAsUnDone(int id) {
        for (Task task : this) {
            if (task.hasId(id)) {
                task.markAsUnDone();
                return true;
            }
        }
        return false;
    }

    public String format(String indentation) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this) {
            stringBuilder.append(indentation).append(task).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
