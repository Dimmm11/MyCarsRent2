package controller.command.manager;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ManagerSetPenalty implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try (JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao()) {
            long penalty = Long.parseLong(request.getParameter(Const.PENALTY));
            int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
            orderDao.setPenalty(orderId, BigDecimal.valueOf(penalty));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/managerOrders";
    }
}
