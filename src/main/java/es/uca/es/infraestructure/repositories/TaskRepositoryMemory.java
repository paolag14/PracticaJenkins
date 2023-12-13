package es.uca.es.infraestructure.repositories;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import es.uca.es.contracts.TaskRepository;
import es.uca.es.models.Task;

public class TaskRepositoryMemory implements TaskRepository {
    private Map<String, Task> tasks;
    private Map<String, Task> savedTasks;
    private Map<String, Task> deletedTasks;

    public TaskRepositoryMemory() {
        this.tasks = new HashMap<String,Task>();
        this.savedTasks = new HashMap<String,Task>();
        this.deletedTasks = new HashMap<String,Task>();
    }
    @Override
    public void addTask(Task task) {
        if (tasks.get(task.getId()) != null) {
            throw new IllegalArgumentException("Task already exists");
        }

        if (task.getId() == null || task.getId().isEmpty()) {
            task.setId(UUID.randomUUID().toString());
        }

        tasks.put(task.getId(), task);
    }

    @Override
    public Task getTask(String id) {
        Task task;

        try {
            task = savedTasks.get(id);
        } catch (Exception e) {
            return null;
        }

        return task;
    }

    @Override
    public List<Task> getTasks() {
        return savedTasks.values().stream().toList();
    }

    @Override
    public void removeTask(Task task) {
        deletedTasks.put(task.getId(), task);
        tasks.remove(task.getId());
    }

    @Override
    public void updateTask(Task task) {
        if(savedTasks.get(task.getId()) == null) {
            throw new IllegalArgumentException("Task does not exist");
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public void saveChanges() {
        Set<String> deletedTasks = this.deletedTasks.keySet();
        for (String task : deletedTasks) {
            savedTasks.remove(task);
        }
        savedTasks.putAll(tasks);
        deletedTasks.clear();
    }
}