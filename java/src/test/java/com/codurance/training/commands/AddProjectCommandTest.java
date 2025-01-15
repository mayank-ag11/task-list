package com.codurance.training.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.TestStringNewLineUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddProjectCommandTest {
    @Test
    public void executeAddProjectCommandToAddAProject() {
        // Arrange
        Projects projects = new Projects();
        AddProjectCommand addProjectCommand = new AddProjectCommand(projects);

        // Act
        addProjectCommand.execute("TestProject");

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "TestProject",
                "",
                ""
        );
        assertEquals(expectedOutput, projects.toString());
    }
}
