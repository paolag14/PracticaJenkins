package es.uca.es;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uca.es.infraestructure.repositories.TaskRepositoryMemory;
import es.uca.es.models.Task;

public class TaskRepositoryTest {
    private TaskRepositoryMemory taskRepository;

    @Before
    public void setUp() {
        taskRepository = new TaskRepositoryMemory();
    }

    @Test
    public void testGetTask() {
        Task task = new Task("New Task");
        task.setId("1");

        taskRepository.addTask(task);
        taskRepository.saveChanges();
        Task retrievedTask = taskRepository.getTask("1");

        assertNotNull(retrievedTask);
        assertEquals("1", retrievedTask.getId());
        assertEquals("New Task", retrievedTask.getName());
    }

    @Test
    public void testGetTaskNotFound() {
        Task retrievedTask = taskRepository.getTask("1");
        assertNull(retrievedTask);
    }

    @Test
    public void testAddTask() {
        Task task = new Task("New Task");
        taskRepository.addTask(task);
        taskRepository.saveChanges();

        Task retrievedTask = taskRepository.getTask(task.getId());
        assertNotNull(retrievedTask);
        assertNotNull(retrievedTask.getId());
        assertEquals("New Task", retrievedTask.getName());
    }

    @Test
    public void testGetTasks() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);

        taskRepository.saveChanges();

        List<Task> tasks = taskRepository.getTasks();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    public void testRemoveTask() {
        Task task = new Task("Task 1");
        task.setId("1");
        taskRepository.addTask(task);
        taskRepository.saveChanges();

        taskRepository.removeTask(task);
        taskRepository.saveChanges();

        Task retrievedTask = taskRepository.getTask("1");
        assertNull(retrievedTask);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTaskDoesntExist() {
        Task task = new Task("Task 1");
        taskRepository.addTask(task);
        taskRepository.removeTask(task);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task("Task 1");
        task.setId("1");
        taskRepository.addTask(task);
        taskRepository.saveChanges();

        task.setName("Updated Task 1");
        taskRepository.updateTask(task);

        Task retrievedTask = taskRepository.getTask("1");
        assertNotNull(retrievedTask);
        assertEquals("1", retrievedTask.getId());
        assertEquals("Updated Task 1", retrievedTask.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateTaskDoesNotExist() {
        Task task = new Task("Task 1");
        taskRepository.addTask(task);
        taskRepository.updateTask(task);
    }

    @Test
    public void testSaveChanges() {
        Task task = new Task("Task 1");
        taskRepository.addTask(task);

        taskRepository.saveChanges();

        List<Task> tasks = taskRepository.getTasks();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task));
    }
}