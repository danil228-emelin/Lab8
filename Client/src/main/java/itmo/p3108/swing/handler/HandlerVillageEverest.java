package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;
import itmo.p3108.model.Place;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class HandlerVillageEverest extends MapHandler {
    private static final MapHandler handler = new HandlerVillageForest();
    private final int bridgeX = 510;
    private final int bridgeY = 85;
    private final int rocksStart = 730;

    private final int forestBeginning = 870;
    private final int cleaveEverest = 180;

    @Override

    public void moveElement(Person person, JLabel label) {
        if (person.getTargetPlace().equals(Place.EVEREST) && person.getResp().equals(Place.VILLAGE)) {
            moveElementsToBridge(label);
            reachTarget(label);
            person.setTargetPlace(Place.VILLAGE);
            person.setResp(Place.EVEREST);
        } else {
            handler.moveElement(person, label);
        }
    }

    private void reachTarget(JLabel label) {
        for (var i = 1; i < 10; i++) {
            label.setBounds(label.getX() + i, label.getY() - i, label.getWidth(), label.getWidth());
            sleep(70);
        }
        while (label.getX() != rocksStart) {
            label.setBounds(label.getX() + 1, label.getY(), label.getWidth(), label.getHeight());
            sleep(30);
        }
        for (var i = 1; i < 8; i++) {
            label.setBounds(label.getX(), label.getY() - i, label.getWidth(), label.getHeight());
            sleep(50);
        }
        for (var i = 1; i < 15; i++) {
            label.setBounds(label.getX() + i, label.getY(), label.getWidth(), label.getHeight());
            sleep(50);
        }
        while (label.getX() != forestBeginning) {
            label.setBounds(label.getX() + 1, label.getY(), label.getWidth(), label.getHeight());
            sleep(50);
        }
        changeCoordinatesLines(label, label.getX() + 20, cleaveEverest);
        int x = RANDOM.nextInt(3, 20);
        int y = RANDOM.nextInt(-5, 15);
        changeCoordinatesLines(label, x + label.getX(), y + label.getY());


    }

    private void moveElementsToBridge(JLabel label) {
        changeCoordinatesLines(label, bridgeX, bridgeY);

    }

}