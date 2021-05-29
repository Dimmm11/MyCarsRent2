package controller.command.admin;

import controller.command.Command;
import controller.command.service.PageCalculator;
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
//        List<Client> staff = ClientDAO.getStaff();
//        request.setAttribute("staff", staff);

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Client> allStaff = ClientDAO.getStaff();
        List<Client> staff = ClientDAO.getStaff((page - 1) * 3, 3);
        int numPages = new PageCalculator().getNumPages(allStaff.size());

        request.setAttribute("page", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("staff", staff);

        return "WEB-INF/admin/adminStaff.jsp";
    }
}
