package itmo.p3108.util;

import itmo.p3108.command.*;
import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.OneArgument;
import itmo.p3108.exception.AuthorizeException;
import itmo.p3108.exception.ValidationException;
import itmo.p3108.model.Person;
import itmo.p3108.swing.AddFrame;
import itmo.p3108.swing.AuthFrame;
import itmo.p3108.swing.MainFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Executor process request from client.
 * It handles all authorization issues.
 * It analyzes input from client.
 */
@Slf4j
public class Executor {
    private static final ServerChanel serverChanel = new ServerChanel(4445);
    private static final Invoker invoker = Invoker.getInstance();
    private Thread mainFrameThread;


    public void processRequest() {
        ShutDownThread.createAndAdd(serverChanel::close);
        try {
            authorize();
        } catch (AuthorizeException exception) {
            log.error(exception.getMessage());
            System.exit(-1);
        }

    }

    private void authorize() {
        AuthFrame authorizationFrame = new AuthFrame();
        authorizationFrame.createFrame();
        Users users = Users.getUser();
        while (!AuthFrame.ButtonActionListener.wasClicked) {
        }
        log.info("AuthorizationFrame:Button clicked");
        AtomicReference<String> reply = new AtomicReference<>();

        boolean serializeResult = SerializeObject.serialize(AuthFrame.ButtonActionListener.commandOptional.get(), serverChanel.getClientPort());

        if (!serializeResult) {
            throw new AuthorizeException("Can't send Message to server");
        }
        reply.set(serverChanel.sendAndReceive());
        if (reply.get().isEmpty()) {
            throw new AuthorizeException("Connection with server lost,can't authorize now");
        }
        if (reply.get().contains("error")) {
            throw new AuthorizeException(reply.get());
        }
        log.info("Authorized successfully");

        authorizationFrame.close();
        Users.getUser().setToken(reply.get());
        ServerChanel.replyFromServer = null;
        createMainFrameThread(ServerChanel.list, Users.getUser().getLogin());
        if (users.getLogin() == null || users.getPassword() == null) {
            throw new ValidationException("user didn't get password and login");
        }
    }

    private void createMainFrameThread(List<Person> list, String user) {
        log.info("createMainFrameThread started");
        MainFrame mainFrame = new MainFrame();
        mainFrame.createMainFrame();
        Runnable runnable = () -> {
            while (true) {
                while (MainFrame.button == null) {
                }
                log.info("Main frame:Button clicked:" + MainFrame.button.getCommand().name());
                if (MainFrame.button.getCommand() instanceof Add) {
                    Optional<Command> command = FlyWeightCommandFactory.getInstance().getCommand("add");
                    if (command.isEmpty()) {
                        throw new ValidationException("can't find add in FlyWeightCommandFactory ");
                    }
                    Add command1 = (Add) command.get();
                    while (command1.getParameter() == null) {
                        if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                            break;
                        }
                    }
                    if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                        AddFrame.wasClicked = false;
                        AddFrame.wasAdded = false;
                        MainFrame.button = null;
                        continue;
                    }
                }
                if (MainFrame.button.getCommand() instanceof AddIfMax) {
                    Optional<Command> command = FlyWeightCommandFactory.getInstance().getCommand("add_if_max");
                    if (command.isEmpty()) {
                        throw new ValidationException("can't find add in FlyWeightCommandFactory ");
                    }
                    AddIfMax command1 = (AddIfMax) command.get();
                    while (command1.getParameter() == null) {
                        if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                            log.info("ADD BUTTON IS FALSE");
                            break;
                        }
                    }
                    if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                        AddFrame.wasClicked = false;
                        AddFrame.wasAdded = false;
                        MainFrame.button = null;
                        continue;
                    }
                }
                if (MainFrame.button.getCommand() instanceof Update) {
                    Optional<Command> command = FlyWeightCommandFactory.getInstance().getCommand("update");
                    if (command.isEmpty()) {
                        throw new ValidationException("can't find add in FlyWeightCommandFactory ");
                    }
                    Update command1 = (Update) command.get();
                    while (command1.getParameter() == null) {
                        if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                            log.info("UPDATE BUTTON IS FALSE");
                            break;
                        }
                    }
                    if (AddFrame.wasClicked && !AddFrame.wasAdded) {
                        AddFrame.wasClicked = false;
                        AddFrame.wasAdded = false;
                        MainFrame.button = null;
                        continue;
                    }
                }

                AtomicReference<Optional<String>> reply = new AtomicReference<>();
                log.info("Main frame:Try to serialize command");
                boolean serializationResult = SerializeObject.serialize(MainFrame.button.getCommand(), 0);
                if (!serializationResult) {
                    throw new AuthorizeException("Can't send Message to server");
                }
                reply.set(Optional.ofNullable(serverChanel.sendAndReceive()));
                if (reply.get().isEmpty()) {
                    throw new AuthorizeException("Connection with server lost,can't authorize now");
                }

                if (!(MainFrame.button.getCommand() instanceof Info)) {
                    MainFrame.clear();
                    if ((AddFrame.wasClicked && AddFrame.wasAdded)) {
                        MainFrame.addElements();
                        AddFrame.wasClicked = false;
                        AddFrame.wasAdded = false;
                        ((OneArgument) MainFrame.button.getCommand()).setParameter(null);
                    }
                }
                if (MainFrame.button.getCommand() instanceof Clear) {
                    MainFrame.addElements();
                }
                if (MainFrame.button.getCommand() instanceof Info) {
                    MainFrame.button.buttonAction();
                }
                MainFrame.button = null;
            }
        };
        mainFrameThread = new Thread(runnable, "MainFrameThread");
        mainFrameThread.start();
    }
}
