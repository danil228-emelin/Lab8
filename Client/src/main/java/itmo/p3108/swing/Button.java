package itmo.p3108.swing;

import itmo.p3108.command.type.Command;
import lombok.Data;

import javax.swing.*;

@Data
 abstract public class Button {

    protected String english;
    protected String russian;

    protected JButton button;

    protected Command command;
    protected JFrame jFrame;
    protected JPanel jPanel;

    public Button(String english, String russian) {
        this.english = english;
        button = new JButton(english);
        this.russian = russian;

    }

    public static void makeInvisible(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    public void makeInvisible() {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

  abstract   public void buttonAction();

}
