package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetOrderStatus implements Command {
    private static final Logger logger = LogManager.getLogger(SetOrderStatus.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("SetOrderStatus...");
        String orderStatus = request.getParameter(Const.ORDER_STATUS);
        logger.info("order status: "+orderStatus);
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setOrderStatus(orderId, orderStatus);
        return "/managerOrders";
    }
}
