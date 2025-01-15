package com.codurance.training.tasks;

import java.util.ArrayList;

class Tasks extends ArrayList<Task> {

    boolean markTaskByIdAsDone(int id) {
        for (Task task : this) {
            if (task.hasId(id)) {
                task.markAsDone();
                return true;
            }
        }
        return false;
    }

    boolean markTaskByIdAsUnDone(int id) {
        for (Task task : this) {
            if (task.hasId(id)) {
                task.markAsUnDone();
                return true;
            }
        }
        return false;
    }

    String format(String indentation) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this) {
            stringBuilder.append(indentation).append(task.format()).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
