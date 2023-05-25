package itmo.p3108.util;

import itmo.p3108.command.Exit;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.OneArgument;
import itmo.p3108.exception.AuthorizeException;
import itmo.p3108.swing.AuthorizationFrame;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Executor process request from client.
 * It handles all authorization issues.
 * It analyzes input from client.
 */
@Slf4j
public class Executor {
    private static final ServerChanel serverChanel = new ServerChanel(4445);
    private static final Invoker invoker = Invoker.getInstance();

    private static void serializeAndSend(Optional<Command> command, Consumer<Boolean> consumer) {

        command.ifPresentOrElse(
                command1 -> {
                    boolean serializedObject = SerializeObject.serialize(command1, serverChanel.getClientPort());
                    consumer.accept(serializedObject);
                }, () -> {
                    Optional<String> reply = serverChanel.sendAndReceive();
                    if (reply.isPresent()) {
                        System.out.println(reply.get());
                    } else {
                        log.error("Doesn't have reply");
                    }
                });

    }

    public void processRequest() {
        ShutDownThread.createAndAdd(serverChanel::close);
        try {
            authorize();
        } catch (AuthorizeException exception) {
            log.error(exception.getMessage());
            new Exit().prepare();
        }
        while (true) {

            Optional<Command> command = invoker.invoke(UserReader.read());
            serializeAndSend(command,
                    result -> {
                        if (result) {
                            Optional<String> reply = serverChanel.sendAndReceive();
                            if (reply.isPresent()) {
                                System.out.println(reply.get());
                            } else {
                                log.error("Doesn't have reply");
                            }
                        } else {
                            log.error("Can't send serialized message,it is empty");
                        }
                    }
            );
        }
    }

    private void authorize() {
        ButtonActionListener buttonActionListener = new ButtonActionListener();
        AuthorizationFrame authorizationFrame = new AuthorizationFrame();
        JFrame frame = authorizationFrame.createFrame(buttonActionListener);
        Users users = Users.getUser();
        while (users.getLogin() == null || users.getPassword() == null) {

        }
    }

    @Setter
    @NoArgsConstructor
    public static class ButtonActionListener extends AbstractAction {
        private JTextField fieldLogin;
        private JPasswordField passwordField;
        private OneArgument<?> command;

        public ButtonActionListener(ButtonActionListener listener) {
            this.fieldLogin = listener.fieldLogin;
            this.passwordField = listener.passwordField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String login = fieldLogin.getText();
            String password = Arrays.toString(passwordField.getPassword());
           Optional<Command> commandOptional = command.prepare(login + "~" + password);
            AtomicReference<Optional<String>> reply = new AtomicReference<>(Optional.empty());
           serializeAndSend(commandOptional, result -> {
                if (!result) {
                    throw new AuthorizeException("Can't send Message to server");
                }
                reply.set(serverChanel.sendAndReceive());
                if (reply.get().isEmpty()) {
                    throw new AuthorizeException("Connection with server lost,can't authorize now");
                }
                if (reply.get().get().contains("error")) {
                    throw new AuthorizeException(reply.get().get());
                }
                log.info("Authorized successfully");
                Users.getUser().setToken(reply.get().get());
            });
        }
    }
}
