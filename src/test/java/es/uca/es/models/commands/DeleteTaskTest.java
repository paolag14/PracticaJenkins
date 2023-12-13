package es.uca.es.models.commands;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import es.uca.es.infraestructure.repositories.TaskRepositoryMemory;
import es.uca.es.models.Task;

public class DeleteTaskTest {
    @Rule
    public final TextFromStandardInputStream systemIn = TextFromStandardInputStream.emptyStandardInputStream();
    private TaskRepositoryMemory taskRepository;

    @Before
    public void setUp() {
        taskRepository = new TaskRepositoryMemory();
        Task task1 = new Task("Tarea 1");
        Task task2 = new Task("Tarea 2");
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
        taskRepository.saveChanges();
    }
    @Test
    public void testExecute() {
        systemIn.provideLines("1");
        DeleteTask deleteTask = new DeleteTask(taskRepository);
        deleteTask.execute();

        assertEquals(1, taskRepository.getTasks().size());


    }
}
