package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CarReturn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        int clientId = Integer.parseInt(request.getParameter(Const.CLIENT_ID));
        new OrderService().carReturn(orderId, clientId);
        return "/managerOrders";
    }
}
