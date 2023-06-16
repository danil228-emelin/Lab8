package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerRockForest extends MapHandler {
    private static final MapHandler handler = new HandlerRockVillage();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.ROCK) && person.getTargetPlace().equals(Place.FOREST)) {
            for (var i = 1; i < 20; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            }
            label.setBounds(label.getX() - RANDOM.nextInt(5, 10), label.getY() - RANDOM.nextInt(5, 10), label.getWidth(), label.getHeight());
            person.setResp(Place.FOREST);
            person.setTargetPlace(Place.ROCK);
        } else {
            handler.moveElement(person, label);
        }
    }
}
