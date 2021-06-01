package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.util.pagination.PageCalculator;
import model.DAO.myOldDAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Profile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try(JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao();
            JDBCCarDao carDao = (JDBCCarDao)JDBCDaoFactory.getInstance().createCarDao()){
            List<Order> orders = OrderDAO.getOrdersByClient((Client) request
                    .getSession()
                    .getAttribute(Const.CLIENT));
            int page = 1;
            if(request.getParameter(Const.PAGE)!=null){
                page = Integer.parseInt(request.getParameter(Const.PAGE));
            }
            List<Order> ordersOnPage = orderDao.getOrdersByClient((Client) request
                    .getSession()
                    .getAttribute(Const.CLIENT), (page-1)*3, 3);
            List<Car> carsOnPage = carDao.getCarsByClient((Client) request
                    .getSession()
                    .getAttribute(Const.CLIENT), (page-1)*3, 3);
            int numPages = new PageCalculator().getNumPages(orders.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.ORDERS, ordersOnPage);
            request.setAttribute(Const.CARS, carsOnPage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/WEB-INF/client/profile.jsp";
    }
}
