package controller.command.admin;

import controller.command.Command;
import model.DAO.CarDAO;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * all cars from DB
 */
public class AdminCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Car> allCars = CarDAO.getAllCars();
        List<Car> cars =CarDAO.getAllCars((page-1)*3, 3);
        return "/WEB-INF/admin/managerCars.jsp";

    }


}
