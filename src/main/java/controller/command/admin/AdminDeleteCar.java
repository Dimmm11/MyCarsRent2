package controller.command.admin;

import controller.command.Command;
import model.DAO.CarDAO;

import javax.servlet.http.HttpServletRequest;

/**
 * remove car from DB
 */
public class AdminDeleteCar implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter("carId"));
        CarDAO.deleteCar(carId);
        return "managerCars";
    }
}
