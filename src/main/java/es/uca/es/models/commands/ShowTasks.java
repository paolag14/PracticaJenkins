package es.uca.es.models.commands;

import java.util.List;

import es.uca.es.contracts.Command;
import es.uca.es.contracts.TaskRepository;
import es.uca.es.models.Task;

public class ShowTasks implements Command {
    private TaskRepository taskRepository;

    public ShowTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute() {
        List<Task> tasks = taskRepository.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Id")
                .append(" | ")
                .append("Nombre")
                .append(" | ")
                .append("Descripción")
                .append(" | ")
                .append("Fecha de caducidad")
                .append(" | ")
                .append("Completada\n");

        for (Task task : tasks) {
            builder.append(task.toString()).append("\n");
        }
    }


}
