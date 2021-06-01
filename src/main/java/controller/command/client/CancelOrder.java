package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;

import javax.servlet.http.HttpServletRequest;

/**
 * Cancel order status 'ordered'
 * in DB
 */
public class CancelOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new OrderService().cancelOrder(Integer.parseInt(request.getParameter(Const.ORDER_ID)));
        return "/profile";
    }
}
