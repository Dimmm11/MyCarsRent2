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
 * add Car to DB
 */
public class AdminCarAdd implements Command {
    private static final Logger logger = LogManager.getLogger(AdminCarAdd.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("AdminCarAdd...");
        Car car = new Car(
                request.getParameter(Const.MARQUE),
                request.getParameter(Const.CAR_CLASS),
                request.getParameter(Const.MODEL),
                BigDecimal.valueOf(Double.parseDouble(request.getParameter(Const.PRICE)))
        );
        try {
            new CarService().addCar(car);
            logger.info("Car {}", car);
        }catch (Exception e){
            logger.info("Failed add car {}", car);
        }
        return "redirect:/managerCars";
    }
}
