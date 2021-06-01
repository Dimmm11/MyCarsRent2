package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class SetOrderStatus implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String reason = request.getParameter(Const.ORDER_STATUS);
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setOrderStatus(orderId, reason);
        return "/managerOrders";
    }
}
