package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * update Price for a Car
 */
public class UpdatePrice implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()) {
            Car car = carDao.getCarById(Integer.parseInt(request.getParameter(Const.ID)));
            carDao.updatePrice(BigDecimal.valueOf(Long.parseLong(request.getParameter(Const.PRICE))), car);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "managerCars";
    }
}
