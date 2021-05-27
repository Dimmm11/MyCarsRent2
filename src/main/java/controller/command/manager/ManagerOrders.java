package controller.command.manager;

import controller.command.Command;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerOrders implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getAllOrders();
        request.setAttribute("orders", orders);

        List<Car> cars = CarDAO.getOrderedCars();
        request.setAttribute("cars", cars);
        System.out.println("managerOrders.jsp");
        System.out.println(orders.size());
        System.out.println(cars.size());
        return "/WEB-INF/manager/managerOrders.jsp";
    }


}
