package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TestStringNewLineUtils;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class CheckCommandTest {
    @Test
    public void executeCheckCommandToCheckATask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");
        projects.addTaskToProjectWithName("TestProject", new Task(1, "Task1", false));

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        CheckCommand checkCommand = new CheckCommand(projects, printWriter);

        // Act
        checkCommand.execute("1");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [x] 1: Task1",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.toString());
    }

    //TODO missing abstraction in checkcommand - test
    @Test
    public void executeCheckCommandToCheckANonExistingTask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        CheckCommand checkCommand = new CheckCommand(projects, printWriter);

        // Act
        checkCommand.execute("1");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "Could not find a task with an ID of 1.",
                ""
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
