package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * set Rights to user, depending
 * on request parameter
 */
public class StaffRights implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String adminAction = request.getParameter(Const.ADMIN_ACTION);
        if (adminAction.equals(Const.REMOVE_MANAGER)) {
            new ClientService().removeManager(request.getParameter(Const.LOGIN));
            return "adminStaff";
        } else {
            new ClientService().makeManager(request.getParameter(Const.LOGIN));
            return "managerClients";
        }
    }
}
