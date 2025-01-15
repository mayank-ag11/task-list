package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectTest {
    @Test
    public void addTask() {
        // Arrange
        Project project = new Project("TestProject");

        // Act
        project.addTask(new Task(1, "Task1", true));

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                ""
        );
        assertEquals(expectedOutput, project.format());
    }

    @Test
    public void markTaskByIdAsDone() {
        // Arrange
        Project project = new Project("TestProject");
        project.addTask(new Task(1, "Task1", false));

        // Act
        project.markTaskByIdAsDone(1);

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                ""
        );
        assertEquals(expectedOutput, project.format());
    }

    @Test
    public void markTaskByIdAsUnDone() {
        // Arrange
        Project project = new Project("TestProject");
        project.addTask(new Task(1, "Task1", true));

        // Act
        project.markTaskByIdAsUnDone(1);

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                ""
        );
        assertEquals(expectedOutput, project.format());
    }

    @Test
    public void formatProjectContainingASingleTask() {
        // Arrange
        Project project = new Project("TestProject");
        project.addTask(new Task(1, "Task1", false));

        // Act & Assert
        String expectedOutput = TestStringNewLineUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                ""
        );
        assertEquals(expectedOutput, project.format());
    }

    @Test
    public void formatProjectContainingAFewTasks() {
        // Arrange
        Project project = new Project("TestProject");
        project.addTask(new Task(1, "Task1", false));
        project.addTask(new Task(2, "Task2", false));

        // Act & Assert
        String expectedOutput = TestStringNewLineUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "    [ ] 2: Task2",
                ""
        );
        assertEquals(expectedOutput, project.format());
    }
}
