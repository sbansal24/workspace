package DesignPatterns.Command;

public class CommandPatternTest {
    public static void main(String[] args) {
        CommandReceiver commandReceiver = new RemoteCommandReceiver();
        Command command = new ButtonPressCommand(commandReceiver);
        CommandInvoker invoker = new CommandInvoker(command);
        invoker.execute();
    }
}
