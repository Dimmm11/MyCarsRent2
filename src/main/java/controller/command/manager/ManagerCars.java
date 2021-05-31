package controller.command.manager;

import controller.command.Command;
import model.service.pagination.PageCalculator;
import model.DAO.CarDAO;
import model.entity.Car;
import model.service.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

//        List<Car> allCars = CarDAO.getAllCars();
//        List<Car> cars = CarDAO.getAllCars((page - 1) * 3, 3);
        //================================== НАЧИНАЮ ТВОРИТЬ ДИЧЬ
        HttpSession session = request.getSession();

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
        List<Car> allCars = CarDAO.getAllCars((String)session.getAttribute("column"),(String)session.getAttribute("sortOrder"));
        List<Car> cars =  new Paginator<Car>().getEntitiesForPage(allCars,(page - 1) * 3, (page - 1) * 3+3);
        // ==========================
//        JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao();
//        List<Car> allCars = carDao.getAllCars();
//        List<Car> cars = carDao.getAllCars((page - 1) * 3, 3);
        // =========================

        int numPages = new PageCalculator().getNumPages(allCars.size());

        request.setAttribute("page", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("allCars", cars);
        return "/WEB-INF/manager/managerCars.jsp";

    }

}
