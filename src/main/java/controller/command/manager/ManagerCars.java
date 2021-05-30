package controller.command.manager;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.CarDAO;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        List<Car> allCars = CarDAO.getAllCars();
        List<Car> cars = CarDAO.getAllCars((page - 1) * 3, 3);
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
