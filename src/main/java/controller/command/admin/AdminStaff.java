package controller.command.admin;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * get staff
 */
public class AdminStaff implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Client> staff = ClientDAO.getStaff();
        request.setAttribute("staff", staff);
        return "WEB-INF/admin/adminStaff.jsp";
    }
}
