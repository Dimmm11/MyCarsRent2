package controller.command.manager;

import controller.command.Command;
import model.DAO.OrderDAO;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ManagerSetPenalty implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        long penalty = Long.parseLong(request.getParameter("penalty"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println("Penalty in SetReason = "+penalty);
        System.out.println("ORDER in SetReason = "+orderId);
        OrderDAO.setPenalty(orderId, BigDecimal.valueOf(penalty));

        return "/managerOrders";
    }
}
