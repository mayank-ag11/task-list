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
        String expectedOutput = TestUtils.joinWithTrailingLineSeparator(
                "[ ] 1: Task1",
                "[ ] 2: Task2"
        );
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

    @Test
    public void markATaskAsDone() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", false));

        // Act
        tasks.markTaskByIdAsDone(1);

        // Assert
        String expectedOutput = "[x] 1: Task1" + System.lineSeparator();
        assertEquals(expectedOutput, tasks.format(""));
    }

    @Test
    public void markATaskAsUnDone() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", true));

        // Act
        tasks.markTaskByIdAsUnDone(1);

        // Assert
        String expectedOutput = "[ ] 1: Task1" + System.lineSeparator();
        assertEquals(expectedOutput, tasks.format(""));
    }

    @Test
    public void markTaskAsDoneWithNonExistingId() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", false));

        // Act
        boolean output = tasks.markTaskByIdAsDone(999);

        // Assert
        assertFalse(output);
    }

    @Test
    public void markTaskAsUnDoneWithNonExistingId() {
        // Arrange
        Tasks tasks = new Tasks();
        tasks.add(new Task(1, "Task1", false));

        // Act
        boolean output = tasks.markTaskByIdAsUnDone(999);

        // Assert
        assertFalse(output);
    }
}
