package controller.command.manager;

import controller.command.Command;
import model.DAO.OrderDAO;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;

public class SetReason implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter("reason");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println("REASON in SetReason = "+reason);
        System.out.println("ORDER in SetReason = "+orderId);
        OrderDAO.setReason(orderId,reason);

        return "/managerOrders";
    }
}
