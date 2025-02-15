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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > > > TestProject",
                "    [x] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void addAProject() {
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > TestProject",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void addATask() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > > TestProject",
                "    [ ] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void addATaskToNonExistingProject() {
        // Arrange
        String input = """
                add task TestProject Task1
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> Could not find a project with the name \"TestProject\".",
                "> > "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void checkATask() {
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > > > TestProject",
                "    [x] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void uncheckATask() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
                check 1
                uncheck 1
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > > > > TestProject",
                "    [ ] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void checkANonExistingTask() {
        // Arrange
        String input = """
                add project TestProject
                add task TestProject Task1
                check 2
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
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> > > Could not find a task with an ID of 2.",
                "> TestProject",
                "    [ ] 1: Task1",
                "",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    public void executeInvalidCommand() {
        // Arrange
        String input = """
                invalidCommand
                quit
                """;
        BufferedReader in = new BufferedReader(new StringReader(input));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        TaskList taskList = new TaskList(in, out);

        // Act
        taskList.run();

        // Assert
        String expectedOutput = TestStringNewLineUtils.joinWithoutTrailingLineSeparator(
                "> I don't know what the command \"invalidCommand\" is.",
                "> "
        );
        assertEquals(expectedOutput, stringWriter.toString());
    }
}
