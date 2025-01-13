package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectsTest {
    @Test
    public void addTaskToAnExistingProject() {
        // Arrange
        Projects projects = new Projects();
        projects.put("TestProject", new Tasks());

        // Act
        boolean taskAddedSuccessfully  = projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", true));

        // Assert
        assertTrue(taskAddedSuccessfully);
        String expectedOutput = TestUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                ""
        );
        assertEquals(expectedOutput, projects.toString());
    }

    @Test
    public void addTaskToANonExistingProject() {
        // Arrange
        Projects projects = new Projects();

        // Act
        boolean taskAddedSuccessfully  = projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", true));

        // Assert
        assertFalse(taskAddedSuccessfully);
        assertEquals("", projects.toString());
    }

    @Test
    public void formatProjectsContainingASingleProject() {
        // Arrange
        Projects projects = new Projects();
        projects.put("TestProject", new Tasks());

        projects.get("TestProject").add(new Task(1, "Task1", true));

        // Act & Assert
        String expectedOutput = TestUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                ""
        );

        assertEquals(expectedOutput, projects.toString());
    }

    @Test
    public void formatProjectsContainingAFewProjects() {
        // Arrange
        Projects projects = new Projects();
        projects.put("TestProject", new Tasks());
        projects.put("TestProject2", new Tasks());

        projects.get("TestProject").add(new Task(1, "Task1", true));
        projects.get("TestProject2").add(new Task(2, "Task2", true));

        // Act & Assert
        String expectedOutput = TestUtils.joinWithTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                "",
                "TestProject2",
                "    [x] 2: Task2",
                ""
        );

        assertEquals(expectedOutput, projects.toString());
    }
}
