package controller.command.manager;

import controller.command.Command;
import model.DAO.CarDAO;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Car> allCars = CarDAO.getAllCars();
        request.setAttribute("allCars", allCars);
        return "/WEB-INF/manager/managerCars.jsp";
    }
}
