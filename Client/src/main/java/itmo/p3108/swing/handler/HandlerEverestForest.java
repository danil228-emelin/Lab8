package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;

import javax.swing.*;

public class HandlerEverestForest extends MapHandler{


    @Override
    public void moveElement(Person person, JLabel label) {
        if (person.getResp().equals(Place.EVEREST) && person.getTargetPlace().equals(Place.FOREST)) {
            person.setTargetPlace(Place.VILLAGE);
            new HandlerEverestVillage().moveElement(person, label);
            person.setResp(Place.VILLAGE);
            person.setTargetPlace(Place.FOREST);
            new HandlerVillageForest().moveElement(person, label);
            person.setResp(Place.FOREST);
            person.setTargetPlace(Place.EVEREST);
        }
    }
}
