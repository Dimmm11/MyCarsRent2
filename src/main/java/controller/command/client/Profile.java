package controller.command.client;

import controller.command.Command;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Profile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getOrdersByClient((Client)request
                .getSession()
                .getAttribute("client"));
        request.setAttribute("orders", orders);
        List<Car> cars = CarDAO.getCarsByClient((Client)request
                .getSession()
                .getAttribute("client"));
        request.setAttribute("cars", cars);





        return "/WEB-INF/client/profile.jsp";
    }
}
