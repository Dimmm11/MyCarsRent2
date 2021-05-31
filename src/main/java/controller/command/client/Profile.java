package controller.command.client;

import controller.command.Command;
import model.service.pagination.PageCalculator;
import model.DAO.CarDAO;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Profile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = OrderDAO.getOrdersByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT));
        int page = 1;
        if(request.getParameter(Const.PAGE)!=null){
            page = Integer.parseInt(request.getParameter(Const.PAGE));
        }
        List<Order> ordersOnPage = OrderDAO.getOrdersByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT), (page-1)*3, 3);
        List<Car> carsOnPage = CarDAO.getCarsByClient((Client) request
                .getSession()
                .getAttribute(Const.CLIENT), (page-1)*3, 3);
        int numPages = new PageCalculator().getNumPages(orders.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ORDERS, ordersOnPage);
        request.setAttribute(Const.CARS, carsOnPage);
        return "/WEB-INF/client/profile.jsp";
    }
}
