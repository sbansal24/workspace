package DesignPatterns.Command;

public class CommandInvoker {
    private final Command command;

    public CommandInvoker(Command command) {
        this.command = command;
    }

    public void execute(){
        command.execute();
    }
}
