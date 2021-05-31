package controller.command.client;

import controller.command.Command;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;

import javax.servlet.http.HttpServletRequest;

/**
 * Cancel order status 'ordered'
 * in DB
 */
public class CancelOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try(JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao()){
            orderDao.cancelOrder(Integer.parseInt(request.getParameter(Const.ORDER_ID)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/profile";
    }
}
