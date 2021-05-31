package controller.command.client;

import controller.command.Command;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Cancel order status 'ordered'
 * in DB
 */
public class CancelOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderDAO.cancelOrder(Integer.parseInt(request.getParameter("orderId")));
        return "/profile";
    }
}
