package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerEverestKingdom extends MapHandler {
    private static final MapHandler handler = new HandlerEverestForest();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.EVEREST) && person.getTargetPlace().equals(Place.KINGDOM)) {
            person.setTargetPlace(Place.VILLAGE);
            new HandlerEverestVillage().moveElement(person, label);
            person.setResp(Place.VILLAGE);
            person.setTargetPlace(Place.KINGDOM);
            new HandlerVillageKingdom().moveElement(person, label);
            person.setTargetPlace(Place.EVEREST);
            person.setResp(Place.KINGDOM);
            for (var i = 1; i < 5; i++) {
                int z = 1;
                while (z != Math.abs(i)) {
                    sleep(30);
                    label.setBounds(label.getX() - 1, label.getY() + 1, label.getHeight(), label.getWidth());
                    z++;
                }
            person.setResp(Place.EVEREST);
                person.setTargetPlace(Place.KINGDOM);
            }
        } else {
            handler.moveElement(person, label);
        }
    }
}
