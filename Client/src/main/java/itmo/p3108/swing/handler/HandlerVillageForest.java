package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerVillageForest extends MapHandler {
    private static final MapHandler handler = new HandlerVillageKingdom();
    private int rockStartX = 290;
    private int rockStartY = 255;


    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getTargetPlace().equals(Place.FOREST) && person.getResp().equals(Place.VILLAGE)) {
            changeCoordinatesLines(label, rockStartX, rockStartY);

            moveWithoutAccelerationY(label, 15);
            for (var i = 1; i < 20; i++) {
                int z = 1;
                while (z != i) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY(), label.getHeight(), label.getWidth());
                    z++;
                }
            }
            moveWithoutAccelerationY(label, 10);
            for (var i = 1; i < 10; i++) {
                int z = 1;
                while (z != i) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY(), label.getHeight(), label.getWidth());
                    z++;
                }
            }
            label.setBounds(label.getX() + RANDOM.nextInt(5, 15), label.getY() + RANDOM.nextInt(5, 15), label.getWidth(), label.getHeight());
        person.setResp(Place.FOREST);
        person.setTargetPlace(Place.VILLAGE);
        } else {
            handler.moveElement(person, label);
        }
    }



}

