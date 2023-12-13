package es.uca.es.contracts;

import java.util.List;

import es.uca.es.models.Task;

public interface TaskRepository {
    public void addTask(Task task);
    public void removeTask(Task task);
    public Task getTask(String id);
    public void updateTask(Task task);
    public List<Task> getTasks();
    public void saveChanges();
}
