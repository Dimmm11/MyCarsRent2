package controller.command.manager;

import controller.command.Command;
import controller.command.client.CancelOrder;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * ban Client
 */
public class Ban implements Command {
    private static final Logger logger = LogManager.getLogger(Ban.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Ban: "+request.getParameter(Const.LOGIN));
        new ClientService().ban(request.getParameter(Const.LOGIN));
        return "managerClients";
    }
}
