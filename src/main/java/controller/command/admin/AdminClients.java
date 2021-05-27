package controller.command.admin;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * get Clients
 */
public class AdminClients implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Client> clients = new ArrayList<>();
        clients = ClientDAO.getClients();
        request.setAttribute("adminClients", clients);
        return "/WEB-INF/admin/adminClients.jsp";
    }
}
