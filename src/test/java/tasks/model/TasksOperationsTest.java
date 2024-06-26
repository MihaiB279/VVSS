package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    void F02_incoming_IntervalIsNull_returnOne() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 17, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 21, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 26, 12,30)));
        tasks.add(new Task("TASK4", new Date(80, 04, 29, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(null, null);

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getTitle(), new Task("Empty", new Date()).getTitle());
    }


    @Test
    void F02_incoming_StartlIsNull_returnOne() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 17, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 21, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 26, 12,30)));
        tasks.add(new Task("TASK4", new Date(80, 04, 29, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(null, new Date(80, 04, 22, 12,30));

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getTitle(), new Task("Empty", new Date()).getTitle());
    }

    @Test
    void F02_incoming_EndIsNull_returnOne() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 17, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 21, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 26, 12,30)));
        tasks.add(new Task("TASK4", new Date(80, 04, 29, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(80, 04, 21, 12,30), null);

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getTitle(), new Task("Empty", new Date()).getTitle());
    }

    @Test
    void F02_incoming_tasksIsEmpty_returnOne() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(80, 04, 20, 12,30), new Date(80, 04, 22, 12,30));

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getTitle(), new Task("Empty", new Date()).getTitle());
    }

    @Test
    void F02_incoming_tasksIsNotEmptyAndHasDatesInInterval_returnExpectedTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 15, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 20, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 25, 12,30)));
        tasks.add(new Task("TASK4", new Date(80, 04, 30, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(80, 04, 17, 12,30), new Date(80, 04, 26, 12,30));

        Assertions.assertEquals(2, result.size());
    }

    @Test
    void F02_incoming_tasksIsNotEmptyAndDoesntHaveTasksInInterval_returnOne() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 15, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 20, 12,30)));
        tasks.add(new Task("TASK3", new Date(80, 04, 25, 12,30)));
        tasks.add(new Task("TASK4", new Date(80, 04, 30, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(50, 04, 21, 12,30), new Date(50, 04, 22, 12,30));

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(result.get(0).getTitle(), new Task("Empty", new Date()).getTitle());
    }

    @Test
    void F02_incoming_tasksHasOneElementAndHasDatesInInterval_returnExpectedTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 22, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(80, 04, 17, 12,30), new Date(80, 04, 26, 12,30));

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void F02_incoming_tasksHasMoreElementsAndHasDatesInInterval_returnExpectedTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();

        tasks.add(new Task("TASK1", new Date(80, 04, 19, 12,30)));
        tasks.add(new Task("TASK2", new Date(80, 04, 22, 12,30)));

        TasksOperations top = new TasksOperations(tasks);

        ArrayList<Task> result = (ArrayList<Task>) top.incoming(new Date(80, 04, 17, 12,30), new Date(80, 04, 26, 12,30));

        Assertions.assertEquals(2, result.size());
    }
}
