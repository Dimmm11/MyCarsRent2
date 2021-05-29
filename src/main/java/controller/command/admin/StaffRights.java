package controller.command.admin;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;

/**
 * set Rights to user
 */
public class StaffRights implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String adminAction = request.getParameter("adminAction");
        if(adminAction.equals("removeManager")){
            ClientDAO.removeManager(request.getParameter("login"));
            return "adminStaff";
        }else {
            ClientDAO.makeManager(request.getParameter("login"));
            return "managerClients";
        }
    }
}
