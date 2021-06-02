package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * remove car from DB
 */
public class AdminDeleteCar implements Command {
    private static final Logger logger = LogManager.getLogger(AdminDeleteCar.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("AdminDeleteCar...");
        int carId = Integer.parseInt(request.getParameter(Const.CAR_ID));
        try {
            new CarService().deleteCar(carId);
            logger.info(String.format("car deleted %d", carId));
        } catch (Exception e){
            logger.info(String.format("failed to delete car %d",+carId));
        }
        return "managerCars";
    }
}
