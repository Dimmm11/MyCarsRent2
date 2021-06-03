package model.DAO;

import model.entity.Client;

import java.util.List;

public interface ClientDAO extends GenericDao<Client> {
    List<Client> getClients();

    List<Client> getClients(int index, int offset);

    Client getClient(String login);

    List<Client> getStaff();

    List<Client> getStaff(int index, int offset);

    boolean deleteClient(String login);

    boolean makeManager(String login);

    boolean removeManager(String login);

    boolean register(Client client);

    boolean ban(String login);

    boolean unBan(String login);
}
