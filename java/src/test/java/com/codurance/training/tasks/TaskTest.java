package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void testHasId() {
        // Arrange
        Task task = new Task(1, "Task1", true);

        // Act & Assert
        assertTrue(task.hasId(1));
        assertFalse(task.hasId(99));
    }

    @Test
    public void testFormatForADoneTask() {
        // Arrange
        Task task = new Task(1, "Task1", true);

        // Act
        String output = task.format();

        // Assert
        String expectedOutput = "[x] 1: Task1";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testFormatForANotDoneTask() {
        // Arrange
        Task task = new Task(1, "Task1", false);

        // Act
        String output = task.format();

        // Assert
        String expectedOutput = "[ ] 1: Task1";
        assertEquals(expectedOutput, output);
    }
}
