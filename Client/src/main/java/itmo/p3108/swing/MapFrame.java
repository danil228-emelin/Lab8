package itmo.p3108.swing;

import itmo.p3108.model.Person;
import itmo.p3108.swing.handler.HandlerVillageEverest;
import itmo.p3108.swing.handler.MapHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapFrame extends AbstractFrame {
    private static final MapHandler handler = new HandlerVillageEverest();
    private final Map<Person, JLabel> hashMap = new HashMap<>(18);

    public MapFrame() {
        super.createPanel();
        super.createFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jPanel);
        jFrame.setBounds(DIMENSION.width / 2 - 550, DIMENSION.height / 2 - 350, 1200, 700);
    }


    public void createMapFrame(List<Person> list, JFrame frame) {
        JButton previous = new JButton("<-PREVIOUS");
        JButton move = new JButton("MOVE");
        Button.makeInvisible(move);
        jPanel.add(move);
        move.setForeground(Color.blue);
        move.setBounds(120, 600, 120, 40);
        move.addActionListener(e -> {
            for (Map.Entry<Person, JLabel> entry : hashMap.entrySet()) {
                Thread thread = new Thread(() -> handler.moveElement(entry.getKey(), entry.getValue()));
                thread.start();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ignored) {

                }
            }
        });
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
            setPlaceOnMap(person1, person);
            jPanel.add(person);
        }

        JLabel map = new JLabel(new ImageIcon("D:\\IntelliJ IDEA 2022.2.3\\Lab7\\Client\\src\\main\\resources\\map.jpg"));
        map.setBounds(0, 0, 1200, 700);

        jPanel.add(map);

        jFrame.setVisible(true);


    }

    private void setPlaceOnMap(Person person, JLabel label) {
        Random random = new Random();
        switch (person.getResp()) {
            case ROCK -> label.setBounds(270 + random.nextInt(5, 40), 250 + random.nextInt(20, 40), 30, 30);
            case FOREST -> label.setBounds(110 + random.nextInt(50), 380 + random.nextInt(30), 30, 30);
            case EVEREST -> label.setBounds(900 + random.nextInt(40), 150, 30, 30);
            case KINGDOM -> label.setBounds(450 + random.nextInt(0, 10), 355, 30, 30);
            case VILLAGE -> label.setBounds(395 + random.nextInt(5, 30), 115 + random.nextInt(5, 15), 30, 30);
        }
        hashMap.put(person, label);
    }


}
