package controller.command.admin;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
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
                request.getParameter(Const.MARQUE),
                request.getParameter(Const.CAR_CLASS),
                request.getParameter(Const.MODEL),
                BigDecimal.valueOf(Double.parseDouble(request.getParameter(Const.PRICE)))
        );
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()) {
            carDao.addCar(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/managerCars";
    }
}
