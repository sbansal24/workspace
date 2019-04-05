package DesignPatterns.Command;

public class ButtonPressCommand implements Command {
    private final CommandReceiver commandCommandReceiver;

    public ButtonPressCommand(CommandReceiver commandCommandReceiver) {
        this.commandCommandReceiver = commandCommandReceiver;
    }

    @Override
    public void execute() {
        System.out.println("request for command press has been raised from receiver");
        commandCommandReceiver.pressButton();
    }
}
