package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerEverestRock extends MapHandler {
    private static final MapHandler handler = new HandlerEverestKingdom();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.EVEREST) && person.getTargetPlace().equals(Place.ROCK)) {
            person.setTargetPlace(Place.VILLAGE);
            new HandlerEverestVillage().moveElement(person, label);
            person.setResp(Place.VILLAGE);
            person.setTargetPlace(Place.ROCK);
            new HandlerVillageRock().moveElement(person, label);
            person.setResp(Place.ROCK);
            person.setTargetPlace(Place.EVEREST);
        } else {
            handler.moveElement(person, label);
        }
    }
}
