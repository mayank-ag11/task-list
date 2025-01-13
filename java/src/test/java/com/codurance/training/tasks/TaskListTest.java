package com.codurance.training.tasks;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class TaskListTest {
    @Test
    public void showAProjectWithNoTasks() {
        // Arrange
        String input = """
                add project TestProject
                show
                quit
                """;
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        TaskList taskList = new TaskList(in, out);

        // Act
        taskList.run();

        // Assert
        String expectedOutput = buildExpectedOutput(
                "> > TestProject",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void showAProjectWithAFewTasks() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
                add task TestProject Task2
                show
                quit
                """;
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        TaskList taskList = new TaskList(in, out);

        // Act
        taskList.run();

        // Assert
        String expectedOutput = buildExpectedOutput(
                "> > > > TestProject",
                "    [ ] 1: Task1",
                "    [ ] 2: Task2",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void showAFewProjectsAndAFewTasks() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
                add task TestProject Task2
                add project TestProject2
                add task TestProject2 Task3
                show
                quit
                """;
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        TaskList taskList = new TaskList(in, out);

        // Act
        taskList.run();

        // Assert
        String expectedOutput = buildExpectedOutput(
                "> > > > > > TestProject",
                "    [ ] 1: Task1",
                "    [ ] 2: Task2",
                "",
                "TestProject2",
                "    [ ] 3: Task3",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void showAProjectWithADoneTask() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
                check 1
                show
                quit
                """;
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        TaskList taskList = new TaskList(in, out);

        // Act
        taskList.run();

        // Assert
        String expectedOutput = buildExpectedOutput(
                "> > > > TestProject",
                "    [x] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    private String buildExpectedOutput(String... lines) {
        return String.join(System.lineSeparator(), lines);
    }
}
