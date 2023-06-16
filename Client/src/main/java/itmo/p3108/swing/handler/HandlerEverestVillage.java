package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerEverestVillage extends MapHandler {
    private static final MapHandler handler = new HandlerEverestRock();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getTargetPlace().equals(Place.VILLAGE) && person.getResp().equals(Place.EVEREST)) {
            for (var i = 1; i < 19; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() - 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }

            for (var i = 1; i < 10; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 8; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() - 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 15; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY(), label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 6; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX(), label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 12; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY(), label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 6; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX(), label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 5; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = 1; i < 12; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            label.setBounds(label.getX() - RANDOM.nextInt(5, 15), label.getY(), label.getHeight(), label.getHeight());
        person.setResp(Place.VILLAGE);
        person.setTargetPlace(Place.EVEREST);
        } else {
            handler.moveElement(person, label);
        }
    }
}
