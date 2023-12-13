package es.uca.es.models.validators;

import java.time.LocalDate;

public class TaskValidator {
    public boolean ValidateName(String name) {
        return name != null && !name.isEmpty() && !name.isBlank();
    }

    public boolean ValidatePriority(Integer priority) {
        try {
            return priority >= 0 && priority <= 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean ValidateDueDate(LocalDate dueDate){
        return !dueDate.isBefore(LocalDate.now());
    }
}
