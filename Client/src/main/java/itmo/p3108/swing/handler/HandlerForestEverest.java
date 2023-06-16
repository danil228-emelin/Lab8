package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerForestEverest extends MapHandler {
    private static final MapHandler handler = new HandlerRockForest();

    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.FOREST) && person.getTargetPlace().equals(Place.EVEREST)) {
           person.setTargetPlace(Place.VILLAGE);
            new HandlerForestVillage().moveElement(person, label);
            person.setTargetPlace(Place.EVEREST);
            person.setResp(Place.VILLAGE);
            new HandlerVillageEverest().moveElement(person, label);
            person.setTargetPlace(Place.FOREST);
            person.setResp(Place.EVEREST);
        } else {
            handler.moveElement(person, label);
        }
    }
}
