package controller.command.manager;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.DAO.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SetReason implements Command {
    private static final Logger logger = LogManager.getLogger(SetReason.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("SetReason...");
        String reason = request.getParameter(Const.REASON);
        logger.info("Reason: "+reason);
        int orderId = Integer.parseInt(request.getParameter(Const.ORDER_ID));
        new OrderService().setReason(orderId, reason);
        return "/managerOrders";
    }
}
