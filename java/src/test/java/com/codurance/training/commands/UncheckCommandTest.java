package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TestStringNewLineUtils;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class UncheckCommandTest {
    @Test
    public void executeUnCheckCommandToUnCheckATask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");
        projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", true));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        UncheckCommand uncheckCommand = new UncheckCommand(projects, printWriter);

        // Act
        uncheckCommand.execute("1");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.format());
    }

    @Test
    public void executeUnCheckCommandToUnCheckANonExistingTask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        UncheckCommand uncheckCommand = new UncheckCommand(projects, printWriter);

        // Act
        uncheckCommand.execute("1");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "Could not find a task with an ID of 1.",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
