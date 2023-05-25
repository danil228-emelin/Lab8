package itmo.p3108.swing;

import javax.swing.*;
import java.awt.*;

abstract public class AbstractFrame {

    protected static JFrame logFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(false);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 450, dimension.height / 2 - 200, 800, 500);
        return jFrame;
    }


}
