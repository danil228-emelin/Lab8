package itmo.p3108.swing;

import itmo.p3108.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class MapFrame extends AbstractFrame {

    public MapFrame() {
        super.createPanel();
        super.createFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        jFrame.setBounds(DIMENSION.width / 2 - 550, DIMENSION.height / 2 - 350, 1200, 700);
    }

    public static void main(String[] args) {
    }

    public void createMapFrame(List<Person> list, JFrame frame) {
        JButton previous = new JButton("<-PREVIOUS");
        Button.makeInvisible(previous);
        previous.setForeground(Color.red);
        previous.setBounds(0, 600, 120, 40);
        jPanel.add(previous);
        previous.addActionListener(l -> {
            frame.setVisible(true);
            jFrame.dispose();
            jFrame = null;
            jPanel = null;
        });
        for (Person person1 : list) {
            JLabel person = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\person.jpg"));
            person.setBounds(person1.getCoordinates().getCoordinatesX(), person1.getCoordinates().getCoordinatesY().intValue(), 30, 30);
            jPanel.add(person);
        }

        JLabel map = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\map.jpg"));
        map.setBounds(0, 0, 1200, 700);

        jPanel.add(map);


        jFrame.setVisible(true);
    }

    private static class DrawPerson extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Line2D body = new Line2D.Float(20, 20, 20, 50);
            Line2D leftLeg = new Line2D.Float(20, 50, 10, 70);
            Line2D rightLeg = new Line2D.Float(20, 50, 30, 70);
            Ellipse2D head = new Ellipse2D.Float(10, 0, 20, 20);
            g2.draw(head);
            g2.draw(rightLeg);
            g2.draw(leftLeg);
            g2.draw(body);
        }

    }
}
