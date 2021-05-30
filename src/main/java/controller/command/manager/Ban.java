package controller.command.manager;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;

/**
 * ban Client
 */
public class Ban implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ClientDAO.ban(request.getParameter("login"));
        return "managerClients";
    }
}
