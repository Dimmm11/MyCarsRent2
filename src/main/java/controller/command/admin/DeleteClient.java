package controller.command.admin;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;

/**
 * delete Client from DB
 */
public class DeleteClient implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ClientDAO.deleteClient(request.getParameter("login"));
        return "managerClients";
    }
}
