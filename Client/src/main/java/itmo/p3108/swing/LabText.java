package itmo.p3108.swing;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LabText {
    public static final Map<String, JTextField> map = new HashMap<>();
    public static final Map<String, JLabel> hashMap = new HashMap<>();

    private JPanel panel;

    public LabText(JPanel panel) {
        this.panel = panel;
    }

    public static void clear() {
        for (JTextField field : map.values()) {
            field.setText("");
        }
    }

    public JTextField create(String labelName, int x, int y, int width, int height) {
        if (map.containsKey(labelName)) {
            return map.get(labelName);
        }
        JLabel label = new JLabel(labelName);
        label.setForeground(Color.BLUE);
        label.setBounds(x, y, width, height);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(x + width + 5, y, 120, height);
       jTextField.setForeground(Color.BLUE);
        panel.add(label);
        panel.add(jTextField);
        map.put(labelName, jTextField);
        return jTextField;
    }

    public JTextField create(String labelName, int x, int y, int width, int height, String text) {
        if (map.containsKey(labelName)) {
            return map.get(labelName);
        }
        JLabel label = new JLabel(labelName);
    label.setForeground(Color.BLUE);
        label.setBounds(x, y, width, height);
        JLabel description = new JLabel(text);
        JTextField jTextField = new JTextField();
        jTextField.setForeground(Color.BLUE);
        jTextField.setBounds(x + width + 5, y, 120, height);
        description.setBounds(x+width + 140, y, 150, height);
        panel.add(description);
        panel.add(label);
        panel.add(jTextField);
        map.put(labelName, jTextField);
        return jTextField;
    }

}
