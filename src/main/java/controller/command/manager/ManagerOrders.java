package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ManagerOrders implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao();
             JDBCOrderDao orderDao = (JDBCOrderDao) JDBCDaoFactory.getInstance().createOrderDao()) {
            List<Order> allOrders = orderDao.getAllOrders();
            List<Order> orders = orderDao.getAllOrders((page - 1) * 3, 3);
            List<Car> cars = carDao.getOrderedCars((page - 1) * 3, 3);
            int numPages = new PageCalculator().getNumPages(allOrders.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.CARS, cars);
            request.setAttribute(Const.ORDERS, orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/managerOrders.jsp";
    }


}
