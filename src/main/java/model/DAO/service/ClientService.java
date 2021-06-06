package model.DAO.service;

import model.DAO.ClientDAO;
import model.DAO.DaoFactory;
import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
/**
 * service layer to call ClientDAO
 */
public class ClientService {
    private static final Logger logger = LogManager.getLogger(ClientService.class.getName());
    DaoFactory daoFactory = DaoFactory.getInstance();

    public ClientService() {
    }

    public ClientService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Client> getStaff() {
        List<Client> list = new ArrayList<>();
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            list = clientDao.getStaff();
        } catch (Exception e) {
           logger.info(e.getMessage());
        }
        return list;
    }

    public List<Client> getStaff(int index, int offset) {
        List<Client> list = new ArrayList<>();
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            list = clientDao.getStaff(index,offset);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public Client getClient(String login) {
        Client client = new Client();
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            client = clientDao.getClient(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return client;
    }

    public boolean deleteClient(String login) {
        boolean result=false;
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            result = clientDao.deleteClient(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean removeManager(String login) {
        boolean result=false;
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            result = clientDao.removeManager(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean makeManager(String login) {
        boolean result=false;
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            result = clientDao.makeManager(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean ban(String login) {
        boolean result=false;
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            result = clientDao.ban(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            list = clientDao.getClients();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public List<Client> getClients(int index, int offset) {
        List<Client> list = new ArrayList<>();
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            list = clientDao.getClients(index,offset);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public boolean unBan(String login) {
        boolean result=false;
        try (ClientDAO clientDao = daoFactory.createClientDao()) {
            result = clientDao.unBan(login);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean register(Client client) {
        boolean result=false;
        try (ClientDAO clientDao =  daoFactory.createClientDao()) {
            result = clientDao.register(client);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}
