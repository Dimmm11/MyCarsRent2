package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Setting penalty to order
 */
public class ManagerSetPenalty implements Command {
    private static final Logger logger = LogManager.getLogger(ManagerSetPenalty.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("ManagerSetPenalty...");
        long penalty =0;
        if(!request.getParameter(Const.PENALTY).equals("")){
            penalty = Long.parseLong(request.getParameter(Const.PENALTY));
        }
        logger.info("Penalty: "+penalty);
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setPenalty(orderId, BigDecimal.valueOf(penalty));
        return "/managerOrders";
    }
}
