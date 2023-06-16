package itmo.p3108.swing.handler;

import itmo.p3108.model.Person;

import javax.swing.*;
import java.util.Random;

abstract public class MapHandler {
    protected final Random RANDOM = new Random();

    abstract public void moveElement(Person person, JLabel label);
    protected void changeCoordinatesLines(JLabel label, int x, int y) {

        while (label.getX() != x) {
            if (label.getX() > x) {
                label.setBounds(label.getX() - 1, label.getY(), label.getWidth(), label.getHeight());
            } else {
                if (label.getX() < x) {
                    label.setBounds(label.getX() + 1, label.getY(), label.getWidth(), label.getHeight());
                }
            }
            sleep(30);
        }

        while (label.getY() != y) {
            if (label.getY() > y) {
                label.setBounds(label.getX(), label.getY() - 1, label.getWidth(), label.getHeight());
            } else {
                if (label.getY() < y) {
                    label.setBounds(label.getX(), label.getY() + 1, label.getWidth(), label.getHeight());
                }
            }
            sleep(30);
        }

    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {

        }
    }
    protected void moveWithoutAccelerationY(JLabel label, int count) {
        for (var i = 1; i < count; i++) {
            int z = 1;
            while (z != i) {
                sleep(30);
                label.setBounds(label.getX(), label.getY() + 1, label.getHeight(), label.getWidth());
                z++;
            }
        }
    }
}
