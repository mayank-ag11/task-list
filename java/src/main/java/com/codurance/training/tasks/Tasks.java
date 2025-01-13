package com.codurance.training.tasks;

import java.util.ArrayList;

public class Tasks extends ArrayList<Task> {

    public String format(String indentation) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : this) {
            stringBuilder.append(indentation).append(task).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }
}
