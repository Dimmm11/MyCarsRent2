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
        List<Car> allCars = CarDAO.getAllCars();
        request.setAttribute("allCars", allCars);
        return "/WEB-INF/admin/adminCars.jsp";
    }
}
