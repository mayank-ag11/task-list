package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void testToStringForADoneTask() {
        // Arrange
        Task task = new Task(1, "Task1", true);

        // Act
        String output = task.toString();

        // Assert
        String expectedOutput = "[x] 1: Task1";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testToStringForANotDoneTask() {
        // Arrange
        Task task = new Task(1, "Task1", false);

        // Act
        String output = task.toString();

        // Assert
        String expectedOutput = "[ ] 1: Task1";
        assertEquals(expectedOutput, output);
    }
}
