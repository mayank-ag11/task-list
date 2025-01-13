package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TasksTest {
    @Test
    public void formatTasksContainingASingleTask() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", false));

        // Act
        String output = tasks.format("");

        // Assert
        String expectedOutput = "[ ] 1: Task1" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void formatTasksContainingAFewTasks() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", false));
        tasks.add(new Task(2, "Task2", false));

        // Act
        String output = tasks.format("");

        // Assert
        String expectedOutput = "[ ] 1: Task1" + System.lineSeparator() +
                "[ ] 2: Task2" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    public void formatTasksContainingADoneTask() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", true));

        // Act
        String output = tasks.format("");

        // Assert
        String expectedOutput = "[x] 1: Task1" + System.lineSeparator();
        assertEquals(expectedOutput, output);
    }
}
