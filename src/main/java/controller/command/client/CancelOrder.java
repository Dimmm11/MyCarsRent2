package controller.command.client;

import controller.command.Command;
import controller.command.admin.AdminCarAdd;
import controller.constants.Const;
import model.DAO.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Cancel order status 'ordered'
 * in DB
 */
public class CancelOrder implements Command {
    private static final Logger logger = LogManager.getLogger(CancelOrder.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("CancelOrder...");
        try {
            new OrderService().cancelOrder(Integer.parseInt(request.getParameter(Const.ORDER_ID)));
            logger.info(String.format("Cancel order success: %s", request.getParameter(Const.ORDER_ID)));
        } catch (Exception e) {
            logger.info(String.format("Failed cancel order: %s", e.getMessage()));
            return "/error.jsp";
        }
        return "/profile";
    }
}
