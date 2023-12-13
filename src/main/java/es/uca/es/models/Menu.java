package es.uca.es.models;

import es.uca.es.contracts.Command;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private List<Command> commands;

    public Menu(List<Command> commands) {
        this.commands = commands;
    }

    public Menu(){
        this.commands = new LinkedList<>();
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }

    public String showMenu(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            sb.append(i+1).append(". ").append(commands.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public void executeCommand(int index){
        commands.get(index - 1).execute();
    }
}
