package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.service.CarService;
import model.DAO.service.OrderService;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * Command to get clients orders
 * from DB and show on page
 */
public class Profile implements Command {
    private static final Logger logger = LogManager.getLogger(Profile.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Profile.execute...");
        List<Order> orders = new OrderService().getOrdersByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT));
        logger.info(String.format("orders:%d", orders.size()));
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        List<Order> ordersOnPage = new OrderService().getOrdersByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT), (page - 1) * 3, 3);
        logger.info(String.format("ordersOnPage:%d", ordersOnPage.size()));
        List<Car> carsOnPage = new CarService().getCarsByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT), (page - 1) * 3, 3);
        logger.info(String.format("carsOnPage:%s", carsOnPage.size()));
        int numPages = new PageCalculator().getNumPages(orders.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ORDERS, ordersOnPage);
        request.setAttribute(Const.CARS, carsOnPage);
        return "/WEB-INF/client/profile.jsp";
    }
}
