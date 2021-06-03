package model.DAO;

import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDAO extends GenericDao<Order>{
    boolean carOrder(Car car, Client client, String driver, int term);

    boolean makeOrder(Car car, Client client, String driver, int term);

    List<Order> getOrdersByClient(Client client);

    List<Order> getOrdersByClient(Client client, int index, int offset);

    List<Order> getAllOrders();

    List<Order> getAllOrders(int index, int offset);

    boolean setReason(int orderId, String reason);

    boolean setPenalty(int orderId, BigDecimal penalty);

    boolean setOrderStatus(int orderId, String reason);

    boolean carReturn(int orderId, int clientId);

    boolean cancelOrder(int orderId);
}
