package es.uca.es.models.commands;

import java.util.List;
import java.util.Scanner;

import es.uca.es.contracts.Command;
import es.uca.es.contracts.TaskRepository;
import es.uca.es.models.Task;

public class DeleteTask implements Command {
    private TaskRepository taskRepository;

    public DeleteTask(TaskRepository _taskRepository) {
        taskRepository = _taskRepository;
    }

    @Override
    public void execute() {
        List<Task> tasks = taskRepository.getTasks();
        showTaskList(tasks);
        Scanner scanner = new Scanner(System.in);

        boolean validNumber = false;

        do {
            System.out.println("Selecciona una tarea:");
            int index = Integer.parseInt(scanner.nextLine());
            validNumber = index > 0 && index<=tasks.size();
            if(validNumber)
                taskRepository.removeTask(tasks.get(index - 1));
        } while (!validNumber);

        taskRepository.saveChanges();
    }

    @Override
    public String toString() {
        return "Eliminar tarea";
    }

    private void showTaskList(List<Task> tasks) {
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
        int i = 0;
        for (Task task : tasks) {
            builder.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
    }
}
