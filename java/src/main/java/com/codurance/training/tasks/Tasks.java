package com.codurance.training.tasks;

import java.util.ArrayList;

public class Tasks extends ArrayList<Task> {

    public boolean toggleTaskById(int id, boolean done) {
        for (Task task : this) {
            if (task.getId() == id) {
                task.setDone(done);
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
