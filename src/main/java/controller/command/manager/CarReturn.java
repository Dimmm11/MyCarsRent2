package controller.command.manager;

import controller.command.Command;
import model.DAO.OrderDAO;

import javax.servlet.http.HttpServletRequest;

public class CarReturn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int clientId = Integer.parseInt(request.getParameter("clientId"));

        OrderDAO.carReturn(orderId, clientId);

        return "/managerOrders";
    }
}
