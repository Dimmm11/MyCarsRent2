package controller.command.manager;

import controller.command.Command;
import model.DAO.ClientDAO;

import javax.servlet.http.HttpServletRequest;

public class UnBan implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ClientDAO.unBan(request.getParameter("login"));
        return "managerClients";
    }
}
