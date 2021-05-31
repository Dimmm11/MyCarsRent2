package controller.command.client;

import controller.command.Command;
import model.service.pagination.PageCalculator;
import model.DAO.CarDAO;
import model.entity.Car;
import model.service.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarsByMarque implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars;
        String marque = request.getParameter(Const.MARQUE);
        if(marque==null){
            marque=(String) session.getAttribute(Const.MARQUE);
        }
        session.setAttribute(Const.MARQUE, marque);
        int page=1;
        if(request.getParameter(Const.PAGE)!=null){
            page = Integer.parseInt(request.getParameter(Const.PAGE));
        }
        String column = (String) session.getAttribute(Const.COLUMN);
        if(column==null){
            column=Const.ID;
        }
        String sortOrder = (String) session.getAttribute(Const.SORT_ORDER);
        if(sortOrder==null){
            sortOrder= Const.ASC;
        }
        if(request.getParameter(Const.COLUMN)!=null){
            session.setAttribute(Const.COLUMN, request.getParameter(Const.COLUMN));
        }else {
            session.setAttribute(Const.COLUMN, column);
        }
        if(request.getParameter(Const.SORT_ORDER)!=null){
            session.setAttribute(Const.SORT_ORDER, request.getParameter(Const.SORT_ORDER));
        }else {
            session.setAttribute(Const.SORT_ORDER, sortOrder);
        }
        List<Car> allCars = CarDAO.getCarsByMarque((String)session.getAttribute(Const.MARQUE),
                (String)session.getAttribute(Const.COLUMN),
                (String)session.getAttribute(Const.SORT_ORDER));
        cars = new Paginator<Car>().getEntitiesForPage(allCars,
                (page-1)*3,
                (page - 1) * 3+3);
        int numPages = new PageCalculator().getNumPages(allCars.size());
        request.setAttribute(Const.PAGE,page);
        request.setAttribute(Const.NUM_PAGES,numPages);
        request.setAttribute(Const.CARS_BY_MARQUE, cars);
        request.setAttribute(Const.MARQUE, marque);
        return "/WEB-INF/client/carsByMarque.jsp";
    }
}
