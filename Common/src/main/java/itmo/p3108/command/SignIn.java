package itmo.p3108.command;

import itmo.p3108.command.type.Command;
import itmo.p3108.command.type.OneArgument;
import itmo.p3108.util.Token;
import itmo.p3108.util.Users;
import itmo.p3108.util.UsersStorage;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class SignIn implements OneArgument<Users> {
    private Users users;

    public SignIn() {
        users = Users.getUser();
    }

    @Override
    public String description() {
        return "sign in user in system";
    }

    @Override
    public String name() {
        return "sign_in";
    }

    @Override
    public String execute(String token) {
        if (UsersStorage.isExist(token)) {
            log.info("error:authorization failed for " + users.getLogin());
            return "error:Login Already exist";
        }
        users.setToken(token);
        users.setPassword(Token.encrypt(users.getPassword()));
        UsersStorage.add(token, users);
        log.info("authorized in system " + users.getLogin());
        return token;
    }

    @Override
    public Users getParameter() {
        return users;
    }

    @Override
    public void setParameter(@NonNull Users parameter) {
        users = parameter;
    }

    @Override
    public Optional<Command> prepare(String argument) {
        String[] strings = argument.split("~");
        users.setLogin(strings[0]);
        users.setPassword(strings[1]);
        return Optional.of(this);
    }


}
