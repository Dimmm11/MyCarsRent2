package controller.command.client;

import controller.command.Command;
import controller.command.service.PageCalculator;
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
        Client cl = (Client) request.getSession().getAttribute("client");
        System.out.println("CL is: "+cl.getLogin());
        List<Order> orders = OrderDAO.getOrdersByClient((Client) request
                .getSession()
                .getAttribute("client"));
//        request.setAttribute("orders", orders);
        List<Car> cars = CarDAO.getCarsByClient((Client) request
                .getSession()
                .getAttribute("client"));
//        request.setAttribute("cars", cars);
// =========================================================
        int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Order> ordersOnPage = OrderDAO.getOrdersByClient((Client) request
                .getSession()
                .getAttribute("client"), (page-1)*3, 3);
        System.out.println("orders on page size: "+ordersOnPage.size());
        List<Car> carsOnPage = CarDAO.getCarsByClient((Client) request
                .getSession()
                .getAttribute("client"), (page-1)*3, 3);
        System.out.println("cars on page size: "+carsOnPage.size());
        int numPages = new PageCalculator().getNumPages(orders.size());

        request.setAttribute("page", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("orders", ordersOnPage);
        request.setAttribute("cars", carsOnPage);


        return "/WEB-INF/client/profile.jsp";
    }
}
