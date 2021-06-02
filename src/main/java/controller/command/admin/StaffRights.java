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
 * set Rights to user, depending
 * on request parameter
 */
public class StaffRights implements Command {
    private static final Logger logger = LogManager.getLogger(StaffRights.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("StaffRights...");
        String adminAction = request.getParameter(Const.ADMIN_ACTION);
        if (adminAction.equals(Const.REMOVE_MANAGER)) {
            try {
                new ClientService().removeManager(request.getParameter(Const.LOGIN));
                logger.info(String.format("removed manager from: %s", request.getParameter(Const.LOGIN)));
            }catch (Exception e){
                logger.info(String.format("Failed to remove manager, %s", e.getMessage()));
            }
            return "adminStaff";
        } else {
            try {
                new ClientService().makeManager(request.getParameter(Const.LOGIN));
                logger.info(String.format("added manager : %s", request.getParameter(Const.LOGIN)));
            }catch (Exception e){
                logger.info(String.format("Failed to make manager, %s", e.getMessage()));
            }
            return "managerClients";
        }
    }
}
