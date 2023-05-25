package itmo.p3108.swing;

import itmo.p3108.command.LogIn;
import itmo.p3108.command.SignIn;
import itmo.p3108.util.Executor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class AuthorizationFrame extends AbstractFrame {
    @Setter
    @Getter
    private JFrame frame;

    public AuthorizationFrame() {
        frame = logFrame();
    }


    public JFrame createFrame(Executor.ButtonActionListener action) {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel picture = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\authorization.jpg"));
        picture.setBounds(1, 1, 800, 500);

        JButton logButton = new JButton("LOG");
        logButton.setBounds(195, 400, 70, 30);


        JButton regButton = new JButton("REG");
        regButton.setBounds(520, 400, 70, 30);


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

        action.setFieldLogin(logTextField);
        action.setPasswordField(passwordField);
        action.setCommand(new LogIn());
        logButton.addActionListener(action);

        Executor.ButtonActionListener reg = new Executor.ButtonActionListener(action);
        reg.setCommand(new SignIn());
        regButton.addActionListener(reg);

        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(logLabel);
        panel.add(logTextField);
        panel.add(regButton);
        panel.add(logButton);
        frame.add(panel);
        panel.add(picture);
        frame.revalidate();
        frame.setVisible(true);
        return frame;
    }


}
