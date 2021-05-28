package controller.command.admin;

import controller.command.Command;
import model.DAO.CarDAO;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * update Price for Car
 */
public class UpdatePrice implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Car car = CarDAO.getCarById(Integer.parseInt(request.getParameter("id")));
        CarDAO.updatePrice(BigDecimal.valueOf(Long.parseLong(request.getParameter("price"))),car);
        return "managerCars";
    }
}
