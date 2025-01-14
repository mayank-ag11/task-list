package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.TestUtils;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class AddTaskCommandTest {
    @Test
    public void executeAddTaskCommandToAddATask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        AddTaskCommand addTaskCommand = new AddTaskCommand(projects, printWriter);

        // Act
        addTaskCommand.execute("TestProject", "Task1");

        // Assert
        String expectedOutput = TestUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.toString());
    }

    @Test
    public void executeAddTaskCommandToAddATaskToNonExistingProject() {
        // Arrange
        Projects projects = new Projects();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        AddTaskCommand addTaskCommand = new AddTaskCommand(projects, printWriter);

        // Act
        addTaskCommand.execute("TestProject", "Task1");

        // Assert
        String expectedOutput = TestUtils.joinWithoutTrailingLineSeparator(
                "Could not find a project with the name \"TestProject\".",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
