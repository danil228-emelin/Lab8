package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerRockEverest extends MapHandler {
    private static final MapHandler handler = new HandlerRockKingdom();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.ROCK) && person.getTargetPlace().equals(Place.EVEREST)) {
            person.setTargetPlace(Place.VILLAGE);
            new HandlerRockVillage().moveElement(person, label);
            person.setResp(Place.VILLAGE);
            person.setTargetPlace(Place.EVEREST);
            new HandlerVillageEverest().moveElement(person, label);
            person.setResp(Place.EVEREST);
            person.setTargetPlace(Place.ROCK);
        } else handler.moveElement(person, label);

    }
}
