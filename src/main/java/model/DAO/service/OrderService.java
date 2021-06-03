package model.DAO.service;

import model.DAO.impl.JDBCOrderDao;
import model.DAO.DaoFactory;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Order> getOrdersByClient(Client client, int index, int offset) {
        List<Order> list = new ArrayList<>();
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            list = orderDao.getOrdersByClient(client, index, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Order> getOrdersByClient(Client client) {
        List<Order> list = new ArrayList<>();
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            list = orderDao.getOrdersByClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean cancelOrder(int orderId) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.cancelOrder(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean carOrder(Car car, Client client, String driver, int term) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.carOrder(car,client,driver,term);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean carReturn(int orderId, int clientId) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.carReturn(orderId,clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            list = orderDao.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Order> getAllOrders(int index, int offset) {
        List<Order> list = new ArrayList<>();
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            list = orderDao.getAllOrders(index,offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean setPenalty(int orderId, BigDecimal penalty) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.setPenalty(orderId,penalty);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean setOrderStatus(int orderId, String reason) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.setOrderStatus(orderId,reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean setReason(int orderId, String reason) {
        boolean result=false;
        try (JDBCOrderDao orderDao = (JDBCOrderDao) daoFactory.createOrderDao()) {
            result = orderDao.setReason(orderId,reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
