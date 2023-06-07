package itmo.p3108.util;

import itmo.p3108.command.type.Command;
import itmo.p3108.model.Person;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class MessageServer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;
    private Command command;
    private int port;
    private String token;
    private ArrayList<Person> list;
    private String message;

}
