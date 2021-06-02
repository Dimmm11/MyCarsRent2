package model.DAO.service;

import model.DAO.impl.JDBCClientDao;
import model.DAO.DaoFactory;
import model.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Client> getStaff() {
        List<Client> list = new ArrayList<>();
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            list = clientDao.getStaff();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Client> getStaff(int index, int offset) {
        List<Client> list = new ArrayList<>();
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            list = clientDao.getStaff(index,offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Client getClient(String login) {
        Client client = new Client();
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            client = clientDao.getClient(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
    public boolean deleteClient(String login) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.deleteClient(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean removeManager(String login) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.removeManager(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean makeManager(String login) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.makeManager(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean ban(String login) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.ban(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            list = clientDao.getClients();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Client> getClients(int index, int offset) {
        List<Client> list = new ArrayList<>();
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            list = clientDao.getClients(index,offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean unBan(String login) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.unBan(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean register(Client client) {
        boolean result=false;
        try (JDBCClientDao clientDao = (JDBCClientDao) daoFactory.createClientDao()) {
            result = clientDao.register(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
