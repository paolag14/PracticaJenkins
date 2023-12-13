package es.uca.es.models.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import es.uca.es.infraestructure.repositories.TaskRepositoryMemory;
import es.uca.es.models.Task;

public class AddTaskTest {
    @Rule
    public final TextFromStandardInputStream systemIn = TextFromStandardInputStream.emptyStandardInputStream();
    private TaskRepositoryMemory taskRepository;

    @Before
    public void setUp() {
        taskRepository = new TaskRepositoryMemory();
    }
    @Test
    public void testExecute() {
        systemIn.provideLines("","New Task", "New Description", "-1","1", "2010-01-01","2025-01-01");
        AddTask addTask = new AddTask(taskRepository);
        addTask.execute();

        Task task = taskRepository.getTasks().get(0);

        assertNotNull(task);
        assertEquals(1, taskRepository.getTasks().size());
        assertEquals("New Task", task.getName());
        assertEquals("New Description", task.getDescription());
        assertEquals("2025-01-01", task.getDueDate().toString());
        assertEquals(1, task.getPriority().ordinal());
    }

    @Test
    public void testToString() {
        AddTask addTask = new AddTask(taskRepository);
        assertEquals("Añadir tarea", addTask.toString());
    }
}
