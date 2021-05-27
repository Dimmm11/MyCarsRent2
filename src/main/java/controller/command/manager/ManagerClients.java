package controller.command.manager;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ManagerClients implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Client> clients = new ArrayList<>();
        clients = ClientDAO.getClients();
        request.setAttribute("adminClients", clients); // проверить атрибуты админа-менеджера тут
        return "/WEB-INF/manager/managerClients.jsp";
    }
}
