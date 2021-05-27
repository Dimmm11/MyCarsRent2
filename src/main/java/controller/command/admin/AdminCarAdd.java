package controller.command.admin;

import controller.command.Command;
import model.DAO.CarDAO;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * add Car to DB
 */
public class AdminCarAdd implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Car car = new Car(
                request.getParameter("marque"),
                request.getParameter("car_class"),
                request.getParameter("model"),
//                Double.parseDouble(request.getParameter("price"))
                BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")))
        );
        CarDAO.addCar(car);
        System.out.println("SUCCESS! CAR ADDED");
        return "admincars";
    }
}
