package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ManagerSetPenalty implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        long penalty = Long.parseLong(request.getParameter(Const.PENALTY));
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setPenalty(orderId, BigDecimal.valueOf(penalty));
        return "/managerOrders";
    }
}
