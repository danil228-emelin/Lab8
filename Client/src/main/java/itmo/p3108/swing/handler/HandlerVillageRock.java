package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerVillageRock extends MapHandler {
    private static final MapHandler handler = new HandlerForestVillage();
    private int rockStartX = 290;
    private int rockStartY = 255;

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getTargetPlace().equals(Place.ROCK) && person.getResp().equals(Place.VILLAGE)) {
            changeCoordinatesLines(label, rockStartX, rockStartY);
            moveWithoutAccelerationY(label, 15);
            label.setBounds(label.getX() + RANDOM.nextInt(10), label.getY() + RANDOM.nextInt(10), label.getWidth(), label.getHeight());
            person.setResp(Place.ROCK);
            person.setTargetPlace(Place.VILLAGE);
        } else {
            handler.moveElement(person, label);
        }
    }
}