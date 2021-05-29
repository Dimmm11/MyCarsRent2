package controller.command.manager;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerOrders implements Command {
    @Override
    public String execute(HttpServletRequest request) {


//        List<Order> orders = OrderDAO.getAllOrders();
//        request.setAttribute("orders", orders);
//
//        List<Car> cars = CarDAO.getOrderedCars();
//        request.setAttribute("cars", cars);
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Order> allOrders = OrderDAO.getAllOrders();
        List<Order> orders = OrderDAO.getAllOrders((page - 1) * 3, 3);

        List<Car> allCars = CarDAO.getOrderedCars();
        List<Car> cars = CarDAO.getOrderedCars((page - 1) * 3, 3);

        int numPages = new PageCalculator().getNumPages(allOrders.size());

        request.setAttribute("page", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("cars", cars);
        request.setAttribute("orders", orders);


        return "/WEB-INF/manager/managerOrders.jsp";
    }


}
