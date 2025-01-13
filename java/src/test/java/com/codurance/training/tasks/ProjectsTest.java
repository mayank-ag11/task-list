package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectsTest {
    @Test
    public void formatProjectsContainingASingleProject() {
        // Arrange
        Projects projects = new Projects();
        projects.put("TestProject", new Tasks());

        projects.get("TestProject").add(new Task(1, "Task1", true));

        // Act & Assert
        String expected = "TestProject" + System.lineSeparator() +
                "    [x] 1: Task1" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expected, projects.toString());
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
        String expected = "TestProject" + System.lineSeparator() +
                "    [x] 1: Task1" + System.lineSeparator() +
                System.lineSeparator() +
                "TestProject2" + System.lineSeparator() +
                "    [x] 2: Task2" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expected, projects.toString());
    }
}
