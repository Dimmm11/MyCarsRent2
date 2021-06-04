package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CarReturn implements Command {
    private static final Logger logger = LogManager.getLogger(CarReturn.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Car return order id: "+request.getParameter(Const.ORDER_ID));
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        int clientId = Integer.parseInt(request.getParameter(Const.CLIENT_ID));
        new OrderService().carReturn(orderId, clientId);
        return "/managerOrders";
    }
}
