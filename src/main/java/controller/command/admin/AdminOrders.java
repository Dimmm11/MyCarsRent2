package controller.command.admin;

import controller.command.Command;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * getting Orders
 */
public class AdminOrders implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getAllOrders();
        request.setAttribute("orders", orders);

        List<Car> cars = CarDAO.getOrderedCars();
        request.setAttribute("cars", cars);

        return "/WEB-INF/admin/adminOrders.jsp";
    }
}
