package controller.command;

import model.DAO.myOldDAO.ClientDAO;
import model.entity.Client;
import model.util.CheckClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter("Login");
        String pass = request.getParameter("Password");
        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            session.setAttribute("loginError", "All fields required");
            return "redirect:/login.jsp";
        }
        /**
         * check user in DB
         */
        if (!CheckClient.isValidClient(name, pass)) {
            session.setAttribute("loginError", "Wrong authorization data");
            return "redirect:/login.jsp";
        }
        /**
         * set User to Session
         */
        Client client = (Client) session.getAttribute("client");
        if (client == null) {
            client = ClientDAO.getClient(name);
            session.setAttribute("role", client.getRole_id());
            session.setAttribute("client", client);
            session.setAttribute("clientName", name);
        }
        /**
         * check if User is Banned
         */
        if (client.getStatus().equals("BANNED")) {
            session.setAttribute("role", 0);
            session.setAttribute("client", null);
            request.setAttribute("error", "user is BANNED");
            return "redirect:/login.jsp";
        }
        /**
         * All checks passed, send to Page
         */
        if ((int) session.getAttribute("role") == 3) {
            return "/WEB-INF/admin/welcomeAdmin.jsp";
        }
        if ((int) session.getAttribute("role") == 2) {
            return "/WEB-INF/manager/welcomeManager.jsp";
        }
        return "/WEB-INF/client/menu.jsp";
    }
}
