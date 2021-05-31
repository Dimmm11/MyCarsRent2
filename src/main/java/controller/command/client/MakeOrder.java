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
        HttpSession session = request.getSession();
        Car car = CarDAO.getCarById(Integer.parseInt(request.getParameter(Const.ID)));
        String driver = request.getParameter(Const.DRIVER);
        int term = Integer.parseInt(request.getParameter(Const.TERM));
        Client client = (Client)session.getAttribute(Const.CLIENT);
        request.setAttribute(Const.CAR, car);
        request.setAttribute(Const.DRIVER, driver);
        request.setAttribute(Const.TERM, term);
        OrderDAO.carOrder(car, client, driver, term);
        return "/profile";
    }
}
