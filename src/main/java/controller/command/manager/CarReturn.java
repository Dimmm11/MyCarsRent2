package controller.command.manager;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;

import javax.servlet.http.HttpServletRequest;

public class CarReturn implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        int clientId = Integer.parseInt(request.getParameter(Const.CLIENT_ID));
        try (JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao()) {
            orderDao.carReturn(orderId, clientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/managerOrders";
    }
}
