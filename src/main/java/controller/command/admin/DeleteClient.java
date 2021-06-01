package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * delete Client from DB
 */
public class DeleteClient implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        new ClientService().deleteClient(request.getParameter(Const.LOGIN));
        return "managerClients";
    }
}
