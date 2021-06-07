package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.CarService;
import model.DAO.service.OrderService;
import model.entity.Car;
import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Command accepts order parameters
 * from request to make order
 */
public class MakeOrder implements Command {
    private static final Logger logger = LogManager.getLogger(MakeOrder.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("MakeOrder.execute...");
        HttpSession session = request.getSession();
        Optional<String> driverOptional = Optional.ofNullable(request.getParameter(Const.DRIVER));
        String driver = driverOptional.orElse(Const.NO);
        int term = 1;
        if(!request.getParameter(Const.TERM).equals("")){
            term = Integer.parseInt(request.getParameter(Const.TERM));
        }
        Client client = (Client) session.getAttribute(Const.CLIENT);
        try {
            Car car = new CarService().getCarById(Integer.parseInt(request.getParameter(Const.ID)));
            new OrderService().carOrder(car, client, driver, term);
            request.setAttribute(Const.CAR, car);
            request.setAttribute(Const.DRIVER, driver);
            request.setAttribute(Const.TERM, term);
            logger.info(String.format("Order successful: %s", car));
        }catch (Exception e){
            logger.info(String.format("Failed to make order: %s", e.getMessage()));
            return "/error.jsp";
        }
        return "/profile";
    }
}
