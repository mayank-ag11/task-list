package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TestStringNewLineUtils;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ShowCommandTest {
    @Test
    public void executeShowCommandWithAProjectContainingNoTasks() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        ShowCommand showCommand = new ShowCommand(projects, printWriter);

        // Act
        showCommand.execute();

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void executeShowCommandWithAProjectContainingAFewTasks() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");
        projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", false));
        projects.addTaskToProjectWithName("TestProject", new Task(2, "Task2", false));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        ShowCommand showCommand = new ShowCommand(projects, printWriter);

        // Act
        showCommand.execute();

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "    [ ] 2: Task2",
                "",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void executeShowCommandWithAFewProjectsContainingAFewTasks() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");
        projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", false));
        projects.addTaskToProjectWithName("TestProject", new Task(2, "Task2", false));
        projects.addProject("TestProject2");
        projects.addTaskToProjectWithName("TestProject2", new Task(3, "Task3", false));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        ShowCommand showCommand = new ShowCommand(projects, printWriter);

        // Act
        showCommand.execute();

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "    [ ] 2: Task2",
                "",
                "TestProject2",
                "    [ ] 3: Task3",
                "",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void executeShowCommandWithAProjectContainingADoneTask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");
        projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", false));
        projects.markTaskByIdAsDone(1);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        ShowCommand showCommand = new ShowCommand(projects, printWriter);

        // Act
        showCommand.execute();

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                "",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
