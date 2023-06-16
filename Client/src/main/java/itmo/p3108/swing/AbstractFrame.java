package itmo.p3108.swing;

import javax.swing.*;
import java.awt.*;

abstract public class AbstractFrame {
    public static final Dimension DIMENSION = Toolkit.getDefaultToolkit().getScreenSize();
    public static final  Toolkit TOOLKIT=Toolkit.getDefaultToolkit();
    protected JPanel jPanel;
    protected JFrame jFrame;

    public void createFrame() {
        if (jFrame != null) {
            jFrame.dispose();
        }

        jFrame = new JFrame() {
        };
        jFrame.setVisible(false);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }



    public void createPanel() {
        jPanel = new JPanel();
        jPanel.setLayout(null);
    }
    public void close(){
        jFrame.dispose();
    }
}
