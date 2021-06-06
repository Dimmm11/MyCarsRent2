package model.DAO.service;

import model.DAO.OrderDAO;
import model.DAO.DaoFactory;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * service layer to call OrderDAO
 */
public class OrderService {
    private static final Logger logger = LogManager.getLogger(OrderService.class.getName());

    DaoFactory daoFactory = DaoFactory.getInstance();

    public OrderService() {
    }

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Order> getOrdersByClient(Client client, int index, int offset) {
        List<Order> list = new ArrayList<>();
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            list = orderDao.getOrdersByClient(client, index, offset);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public List<Order> getOrdersByClient(Client client) {
        List<Order> list = new ArrayList<>();
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            list = orderDao.getOrdersByClient(client);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public boolean cancelOrder(int orderId) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.cancelOrder(orderId);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean carOrder(Car car, Client client, String driver, int term) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.carOrder(car, client, driver, term);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean carReturn(int orderId, int clientId) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.carReturn(orderId, clientId);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            list = orderDao.getAllOrders();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public List<Order> getAllOrders(int index, int offset) {
        List<Order> list = new ArrayList<>();
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            list = orderDao.getAllOrders(index, offset);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return list;
    }

    public boolean setPenalty(int orderId, BigDecimal penalty) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.setPenalty(orderId, penalty);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean setOrderStatus(int orderId, String orderStatus) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.setOrderStatus(orderId, orderStatus);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }

    public boolean setReason(int orderId, String reason) {
        boolean result = false;
        try (OrderDAO orderDao = daoFactory.createOrderDao()) {
            result = orderDao.setReason(orderId, reason);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return result;
    }
}
