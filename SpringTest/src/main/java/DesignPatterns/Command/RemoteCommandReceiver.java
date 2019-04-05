package DesignPatterns.Command;

public class RemoteCommandReceiver implements CommandReceiver {
    @Override
    public void pressButton() {
        System.out.println("Button has been pressed");
    }
}
