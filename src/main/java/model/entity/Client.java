package model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * entity
 */
public class Client implements Serializable {

    private String login;
    private int id;
    private String password;
    private String passport;
    private int role_id;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Client(String login) {
        this.login = login;
    }

    public Client() {
    }

    public Client(String login, String password, String passport) {
        this.login = login;
        this.password = password;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                login.equals(client.login) &&
                password.equals(client.password) &&
                passport.equals(client.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, id, password, passport);
    }
}
