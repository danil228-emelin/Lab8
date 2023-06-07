package itmo.p3108.swing;

import itmo.p3108.command.FlyWeightCommandFactory;
import itmo.p3108.command.type.Command;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Optional;

@Slf4j
public class AddButton extends Button {


    public AddButton() {
        super("ADD", "Добавить");
        Optional<Command> optional = FlyWeightCommandFactory.getInstance().getCommand("add");
        optional.ifPresent(value -> {
            command = value;
            log.info("FlyWeightCommandFactory found add command");
        });
    }

    public static void main(String[] args) {
        new AddButton().buttonAction();

    }


    public void buttonAction() {

        AddFrame.getInstance().setCommand(command);
        JFrame frame = AddFrame.getInstance().createFrame();
        frame.setVisible(true);
    }
}