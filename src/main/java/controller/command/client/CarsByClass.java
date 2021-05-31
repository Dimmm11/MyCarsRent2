package controller.command.client;

import controller.command.Command;
import model.service.pagination.PageCalculator;
import model.DAO.CarDAO;
import model.entity.Car;
import model.service.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarsByClass implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars;
        try {
            int page = 1;
            if (request.getParameter(Const.PAGE) != null) {
                page = Integer.parseInt(request.getParameter(Const.PAGE));
            }
            String carClass = request.getParameter(Const.CAR_CLASS);
            if (carClass == null) {
                carClass = (String)session.getAttribute(Const.CAR_CLASS);
            }
            session.setAttribute(Const.CAR_CLASS, carClass);
            String column = (String) session.getAttribute(Const.COLUMN);
            if (column == null) {
                column = Const.ID;
            }
            String sortOrder = (String) session.getAttribute(Const.SORT_ORDER);
            if (sortOrder == null) {
                sortOrder = Const.ASC;
            }
            if (request.getParameter(Const.COLUMN) != null) {
                session.setAttribute(Const.COLUMN, request.getParameter(Const.COLUMN));
            } else {
                session.setAttribute(Const.COLUMN, column);
            }
            if (request.getParameter(Const.SORT_ORDER) != null) {
                session.setAttribute(Const.SORT_ORDER, request.getParameter(Const.SORT_ORDER));
            } else {
                session.setAttribute(Const.SORT_ORDER, sortOrder);
            }
            List<Car> allCars = CarDAO.getCarsByClass((String) session.getAttribute(Const.CAR_CLASS),
                    (String) session.getAttribute(Const.COLUMN),
                    (String) session.getAttribute(Const.SORT_ORDER));
            System.out.println("allCars.size: "+allCars.size());

            cars = new Paginator<Car>().getEntitiesForPage(allCars,
                    (page - 1) * 3,
                    (page - 1) * 3 + 3);
            int numPages = new PageCalculator().getNumPages(allCars.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.CARS_BY_CLASS, cars);
            request.setAttribute(Const.CAR_CLASS, carClass);
            return "/WEB-INF/client/carsByClass.jsp";
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return "redirect:/index.jsp";
        }
    }
}
