package controller;

import model.DAO.OrderDAO;
import model.entity.Order;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = OrderDAO.getAllOrders();
        for (Order o :
                orders) {
            System.out.println(o.getId());
        }
    }
}
