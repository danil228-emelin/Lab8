package itmo.p3108.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Slf4j
public class Users implements Serializable {

    @Serial
    private static final long serialVersionUID = 493737051L;

    private static final Users USER = new Users();
    volatile private String login;
    volatile private String password;
    private String token;
    private Boolean isSaved = false;
    private Integer UserId;

    public Users() {
    }

    public static Users getUser() {
        return USER;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(login, users.login) && Objects.equals(password, users.password) && Objects.equals(token, users.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, token);
    }
}
