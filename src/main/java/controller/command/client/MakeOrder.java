package controller.command.client;

import controller.command.Command;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MakeOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Car car = CarDAO.getCarById(Integer.parseInt(request.getParameter("id")));
        String driver = request.getParameter("driver");
        int term = Integer.parseInt(request.getParameter("term"));

        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute("client");

        request.setAttribute("car", car);
        request.setAttribute("driver", driver);
        request.setAttribute("term", term);

        OrderDAO.carOrder(car, client, driver, term);

        return "/profile";
    }
}
