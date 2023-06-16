package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerVillageKingdom extends MapHandler {
    private static final MapHandler handler = new HandlerVillageRock();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getTargetPlace().equals(Place.KINGDOM) && person.getResp().equals(Place.VILLAGE)) {
            changeCoordinatesLines(label, label.getX() + 120, label.getY() + 180);
            changeCoordinatesLines(label, label.getX() - 80, label.getY() + 30);
            label.setBounds(label.getX() + RANDOM.nextInt(5, 20), label.getY() + RANDOM.nextInt(5, 20), label.getWidth(), label.getHeight());
            person.setResp(Place.KINGDOM);
            person.setTargetPlace(Place.VILLAGE);
        } else {
            handler.moveElement(person, label);
        }
    }
}
