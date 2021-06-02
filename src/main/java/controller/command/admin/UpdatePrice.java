package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.CarService;
import model.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * update Price for a Car
 */
public class UpdatePrice implements Command {
    private static final Logger logger = LogManager.getLogger(UpdatePrice.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("UpdatePrice...");
        Car car = new CarService().getCarById(Integer.parseInt(request.getParameter(Const.ID)));
        logger.info(String.format("car: %s", car));
        try {
            new CarService().updatePrice(BigDecimal.valueOf
                    (Long.parseLong(request.getParameter(Const.PRICE))), car);
            logger.info("Price updated: {}",car);
        }catch (Exception e){
            logger.info(String.format("Failed update price, %s", e.getMessage()));
        }
        return "managerCars";
    }
}
