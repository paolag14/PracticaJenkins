package es.uca.es.models;

import java.time.LocalDate;

import es.uca.es.enums.Priority;

public class Task {
    private String id;
    private String name;
    private String description;
    private boolean isCompleted;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate dueDate;
    private Priority priority;

    public Task(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    
        this.id = "";
        this.name = name;
        this.description = "";
        this.isCompleted = false;
        this.createdAt = LocalDate.now();
        this.priority = Priority.NOT_DEFINED;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id)
               .append(" | ")
               .append(name)
               .append(" | ")
               .append(description)
               .append(" | ")
               .append(dueDate);
        return builder.toString();
    }
}
