package itmo.p3108.swing;

import itmo.p3108.command.FlyWeightCommandFactory;
import itmo.p3108.command.type.Command;

import javax.swing.*;
import java.util.Optional;

public class AddIFMaxButton extends Button {


    public AddIFMaxButton() {
        super("ADD_IF_MAX", "Добавить_макс");
        Optional<Command> optional = FlyWeightCommandFactory.getInstance().getCommand("add_if_max");
        optional.ifPresent(value -> command = value);
    }

    public void buttonAction() {
        AddFrame.getInstance().setCommand(command);
        JFrame frame = AddFrame.getInstance().createFrame();
        frame.setVisible(true);
    }
}