package itmo.p3108.swing;

import itmo.p3108.command.Clear;

public class ClearButton extends Button {
    public ClearButton() {
        super("CLEAR", "Отчистисть");
        command = new Clear();
    }

    @Override
    public void buttonAction() {

    }
}
