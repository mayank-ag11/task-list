package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.TestStringNewLineUtils;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class AddCommandTest {
    @Test
    public void executeAddCommandToAddAProject() {
        // Arrange
        Projects projects = new Projects();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        AddCommand addCommand = new AddCommand(projects, printWriter);

        // Act
        addCommand.execute("project TestProject");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.format());
    }

    @Test
    public void executeAddTaskCommandToAddATask() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("TestProject");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        AddCommand addCommand = new AddCommand(projects, printWriter);

        // Act
        addCommand.execute("task TestProject Task1");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "    [ ] 1: Task1",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.format());
    }
}
