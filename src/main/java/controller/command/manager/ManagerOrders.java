package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.CarService;
import model.DAO.service.OrderService;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
/**
 * Command check DB for orders and
 * showing for manager(or Admin)
 */
public class ManagerOrders implements Command {
    private static final Logger logger = LogManager.getLogger(ManagerOrders.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("ManagerOrders...");
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        List<Order> allOrders = new OrderService().getAllOrders();
        logger.info("allOrders: "+allOrders);
        List<Order> orders = new OrderService().getAllOrders((page - 1) * 3, 3);
        logger.info("orders: "+orders);
        List<Car> cars = new CarService().getOrderedCars((page - 1) * 3, 3);
        logger.info("cars: "+cars);
        int numPages = new PageCalculator().getNumPages(allOrders.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.CARS, cars);
        request.setAttribute(Const.ORDERS, orders);
        return "/WEB-INF/manager/managerOrders.jsp";
    }


}
