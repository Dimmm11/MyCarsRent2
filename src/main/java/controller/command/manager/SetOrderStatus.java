package controller.command.manager;

import controller.command.Command;
import model.DAO.OrderDAO;

import javax.servlet.http.HttpServletRequest;

public class SetOrderStatus implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter("orderStatus");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        System.out.println("REASON in SetOrderStatus = "+reason);
        System.out.println("ORDER in SetOrderStatus = "+orderId);
        OrderDAO.setOrderStatus(orderId,reason);

        return "/managerOrders";
    }
}
