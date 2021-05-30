package controller.command.client;

import controller.command.Command;
import controller.command.service.PageCalculator;
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
        String marque = request.getParameter("marque"); // если нулл, то взять из сессии
//        if(session.getAttribute("marque")==null){         // если передавался - перезаписать параметр в сессии
//            session.setAttribute("marque", marque);
//        }

        if(marque==null){
            marque=(String) session.getAttribute("marque");
        }
        session.setAttribute("marque", marque);

        int page=1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        String column = (String) session.getAttribute("column");
        if(column==null){
            column="id";
        }
        String sortOrder = (String) session.getAttribute("sortOrder");
        if(sortOrder==null){
            sortOrder="ASC";
        }
        if(request.getParameter("column")!=null){
            session.setAttribute("column", request.getParameter("column"));
        }else {
            session.setAttribute("column", column);
        }
        if(request.getParameter("sortOrder")!=null){
            session.setAttribute("sortOrder", request.getParameter("sortOrder"));
        }else {
            session.setAttribute("sortOrder", sortOrder);
        }
        List<Car> allCars = CarDAO.getCarsByMarque((String)session.getAttribute("marque"), (String)session.getAttribute("column"),(String)session.getAttribute("sortOrder"));
        System.out.println("page: "+page);
        cars = new Paginator<Car>().getEntitiesForPage(allCars, (page-1)*3, (page - 1) * 3+3);
        int numPages = new PageCalculator().getNumPages(allCars.size());
        request.setAttribute("page",page);
        request.setAttribute("numPages",numPages);
        request.setAttribute("carsByMarque", cars);
        request.setAttribute("marque", marque);

        return "/WEB-INF/client/carsByMarque.jsp";
    }
}
