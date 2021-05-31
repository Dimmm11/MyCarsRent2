package controller.command.manager;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;

import javax.servlet.http.HttpServletRequest;

public class SetOrderStatus implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try (JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao()) {
            String reason = request.getParameter(Const.ORDER_STATUS);
            int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
            orderDao.setOrderStatus(orderId, reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/managerOrders";
    }
}
