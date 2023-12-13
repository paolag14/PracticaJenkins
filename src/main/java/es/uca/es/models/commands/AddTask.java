package es.uca.es.models.commands;

import java.time.LocalDate;
import java.util.Scanner;

import es.uca.es.contracts.Command;
import es.uca.es.contracts.TaskRepository;
import es.uca.es.enums.Priority;
import es.uca.es.models.Task;
import es.uca.es.models.validators.TaskValidator;

public class AddTask implements Command {
    private TaskRepository taskRepository;
    private Scanner scanner = new Scanner(System.in);
    private TaskValidator validator = new TaskValidator();

    public AddTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute() {
        String name = "";
        do {
            System.out.println("Introduce el nombre de la tarea: ");
            name = scanner.nextLine();
            if (validator.ValidateName(name))
                System.out.println("El nombre no es válido");
        } while (!validator.ValidateName(name));

        Task task = new Task(name);

        System.out.println("Introduce la descripción de la tarea: ");
        String description = scanner.nextLine();
        task.setDescription(description);

        Priority priority = SelectPriority();
        task.setPriority(priority);

        LocalDate dueDate = SelectDueDate();
        task.setDueDate(dueDate);

        taskRepository.addTask(task);
        taskRepository.saveChanges();
    }

    @Override
    public String toString() {
        return "Añadir tarea";
    }

    private Priority SelectPriority() {
        boolean isPriorityValid = false;
        do {
            System.out.println("Introduce la prioridad de la tarea: ");
            try {
                Integer priority = Integer.parseInt(scanner.nextLine());
                isPriorityValid = validator.ValidatePriority(priority);
                if (isPriorityValid)
                    return Priority.values()[priority];

                throw new NumberFormatException();

            } catch (NumberFormatException e) {
                System.out.println(
                        "La prioridad debe ser un número del 0 al 4\n0 = NOT_DEFINED, 1 = LOW, 2 = MEDIUM, 3 = HIGH, 4 = URGENT\n");
            }
        } while (!isPriorityValid);

        return Priority.NOT_DEFINED;
    }

    private LocalDate SelectDueDate() {
        boolean isValidDueDate = false;
        LocalDate dueDate = null;
        do {
            System.out.println("Introduce la fecha de vencimiento de la tarea: ");
            try {
                String dueDateString = scanner.nextLine();
                dueDate = LocalDate.parse(dueDateString);
                isValidDueDate = !dueDate.isBefore(LocalDate.now());
                if (!isValidDueDate) {
                    System.out.println("La fecha de vencimiento no puede ser anterior a la fecha actual");
                }
            } catch (Exception e) {
                System.out.println("La fecha debe tener el formato yyyy-mm-dd");
            }
        } while (!isValidDueDate);

        return dueDate;
    }
}
