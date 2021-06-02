package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * delete Client from DB
 */
public class DeleteClient implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteClient.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("DeleteClient...");
        try {
            new ClientService().deleteClient(request.getParameter(Const.LOGIN));
            logger.info(String.format("Client deleted %s", request.getParameter(Const.LOGIN)));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return "managerClients";
    }
}
