package tasks.model;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksOperationsTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void F02_incoming_tasksIsEmpty_returnOne() {
        // Arrange
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        TasksOperations top = new TasksOperations(tasks);

        // Act
        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(), new Date());

        // Assert
        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    void F02_incoming_tasksIsNotEmptyAndHasDatesInInterval_returnExpectedTasks() {
        // Arrange
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 15, 12,30)));
        // valid
        tasks.add(new Task("TASK2", new Date(80, 04, 20, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 25, 12,30)));
        // end valid
        tasks.add(new Task("TASK4", new Date(80, 04, 30, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        // Act
        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(50, 04, 17, 12,30), new Date(50, 04, 26, 12,30));

        // Assert
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    void F02_incoming_tasksIsNotEmptyAndDoesntHaveTasksInInterval_returnOne() {
        // Arrange
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 15, 12,30)));
        // valid
        tasks.add(new Task("TASK2", new Date(80, 04, 20, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 25, 12,30)));
        // end valid
        tasks.add(new Task("TASK4", new Date(80, 04, 30, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        // Act
        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(50, 04, 21, 12,30), new Date(50, 04, 22, 12,30));

        // Assert
        Assertions.assertEquals(result.size(), 1);
    }


}