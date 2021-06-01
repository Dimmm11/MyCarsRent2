package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * ban Client
 */
public class Ban implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new ClientService().ban(request.getParameter(Const.LOGIN));
        return "managerClients";
    }
}
