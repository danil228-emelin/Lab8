package itmo.p3108.swing;

import itmo.p3108.command.LogIn;
import itmo.p3108.command.SignIn;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.OneArgument;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Optional;

public class AuthFrame extends AbstractFrame {
    private JLabel informationLabel;

    public AuthFrame() {
        createPanel();
        createFrame();
    }

    public static void main(String[] args) {
        new AuthFrame().createFrame();
    }

    @Override
    public void createFrame() {
        super.createPanel();
        super.createFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        jFrame.setBounds(DIMENSION.width / 2 - 450, DIMENSION.height / 2 - 200, 800, 500);

        JLabel picture = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\authorization.jpg"));
        picture.setBounds(1, 1, 800, 500);
        JButton logButton = new JButton("LOG");
        logButton.setForeground(Color.CYAN);
        logButton.setBounds(195, 400, 70, 30);
        itmo.p3108.swing.Button.makeInvisible(logButton);
        JButton regButton = new JButton("REG");
        regButton.setBounds(520, 400, 70, 30);
        regButton.setForeground(Color.MAGENTA);
        Button.makeInvisible(regButton);
        JTextField logTextField = new JTextField();
        logTextField.setBackground(Color.lightGray);
        logTextField.setBounds(200, 150, 300, 30);

        JLabel logLabel = new JLabel("Login:");
        logLabel.setForeground(Color.MAGENTA);
        logLabel.setBounds(160, 150, 40, 30);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(Color.lightGray);
        passwordField.setBounds(200, 250, 300, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.MAGENTA);
        passwordLabel.setBounds(135, 250, 60, 30);
        AuthFrame.ButtonActionListener action = new AuthFrame.ButtonActionListener();

        action.setFieldLogin(logTextField);
        action.setPasswordField(passwordField);
        action.setCommand(new LogIn());
        logButton.addActionListener(action);
        AuthFrame.ButtonActionListener reg = new AuthFrame.ButtonActionListener(action);
        reg.setCommand(new SignIn());
        regButton.addActionListener(reg);
        informationLabel = new JLabel();
        informationLabel.setBounds(300, 50, 200, 20);
        jPanel.add(informationLabel);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(logLabel);
        jPanel.add(logTextField);
        jPanel.add(regButton);
        jPanel.add(logButton);
        jFrame.add(jPanel);
        jPanel.add(picture);
        jFrame.revalidate();
        jFrame.setVisible(true);
    }

    @Setter
    @NoArgsConstructor
    public static class ButtonActionListener extends AbstractAction {
        public volatile static Optional<Command> commandOptional = Optional.empty();
        public volatile static boolean wasClicked = false;
        private JTextField fieldLogin;
        private JPasswordField passwordField;
        private OneArgument<?> command;

        public ButtonActionListener(AuthFrame.ButtonActionListener listener) {
            this.fieldLogin = listener.fieldLogin;
            this.passwordField = listener.passwordField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String login = fieldLogin.getText();
            String password = Arrays.toString(passwordField.getPassword());
            commandOptional = command.prepare(login + "~" + password);
            wasClicked = true;

        }

    }
}
