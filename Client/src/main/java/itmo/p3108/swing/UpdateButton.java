package itmo.p3108.swing;

import itmo.p3108.command.FlyWeightCommandFactory;
import itmo.p3108.command.type.Command;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Optional;

@Slf4j
public class UpdateButton extends Button {
    public static JTextField idText;

    public UpdateButton() {
        super("UPDATE", "Обновить");
        Optional<Command> optional = FlyWeightCommandFactory.getInstance().getCommand("update");
        optional.ifPresent(value -> {
            command = value;
            log.info("FlyWeightCommandFactory found add command");
        });
    }

    public static void main(String[] args) {
        new UpdateButton().buttonAction();
    }

    public void buttonAction() {
        JLabel id = new JLabel("ID");
        idText = new JTextField();
        idText.setBounds(95, 5, 120, 30);
        id.setBounds(50, 5, 30, 30);
        AddFrame addFrame = AddFrame.getInstance();
        addFrame.setCommand(command);

        JFrame frame = addFrame.createFrame();
        addFrame.getJPanel().add(idText);
        addFrame.getJPanel().add(id);
        frame.setVisible(true);
    }

}
