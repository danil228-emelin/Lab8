package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerRockKingdom extends MapHandler {
    private static final MapHandler handler = new HandlerEverestVillage();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.ROCK) && person.getTargetPlace().equals(Place.KINGDOM)) {

            for (var i = -1; i > -18; i--) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() + 1, label.getY() - 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = -1; i > -9; i--) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX(), label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }

            }
            for (var i = -1; i > -15; i--) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() + 1, label.getY(), label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = -1; i > -15; i--) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX(), label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            for (var i = -1; i > -10; i--) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
                label.setBounds(label.getX() - RANDOM.nextInt(5), label.getY() + RANDOM.nextInt(5), label.getHeight(), label.getWidth());
                person.setResp(Place.KINGDOM);
                person.setTargetPlace(Place.FOREST);
            }
        } else {
            handler.moveElement(person, label);
        }
    }
}
