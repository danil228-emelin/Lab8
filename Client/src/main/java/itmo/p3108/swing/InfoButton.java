package itmo.p3108.swing;

import itmo.p3108.command.Info;
import itmo.p3108.util.ServerChanel;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class InfoButton extends Button {
    public InfoButton() {
        super("INFO", "Инфо");
        command = new Info();
    }

    public static void main(String[] args) {
        new InfoButton().buttonAction();

    }


    public void buttonAction() {
        log.info("buttonAction try to perform");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        if (jPanel == null) {
            jPanel = new JPanel();
            jPanel.setBackground(Color.GRAY);
            jPanel.setLayout(null);
            jPanel.setBounds(dimension.width / 2 - 450, dimension.height / 2 - 200, 500, 150);
        }
        if (jFrame != null) {
            jFrame.dispose();
        }
        jFrame = new JFrame() {
        };
        jFrame.setBackground(Color.GRAY);
        jFrame.setVisible(false);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);
        jFrame.setBounds(dimension.width / 2 - 450, dimension.height / 2 - 350, 500, 150);
        JLabel label = new JLabel(ServerChanel.replyFromServer);
        label.setBounds(dimension.width / 2 - 450, dimension.height / 2 - 350, 500, 150);
        jFrame.add(jPanel);
        jFrame.add(label);
        jFrame.setVisible(true);
        jFrame.revalidate();
    }

}
