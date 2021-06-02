package controller.command;

import controller.command.client.Profile;
import model.DAO.myOldDAO.ClientDAO;
import model.entity.Client;
import model.util.CheckClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("LoginCommand.execute...");
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
            logger.info(String.format("Not valid user:%s,%s", name, pass));
            session.setAttribute("loginError", "Wrong authorization data");
            return "redirect:/login.jsp";
        }
        /**
         * set User to Session
         */
        Client client = (Client) session.getAttribute("client");
        if (client == null) {
            client = ClientDAO.getClient(name);
            logger.info(String.format("Set client to session:%s", client));
            session.setAttribute("role", client.getRole_id());
            session.setAttribute("client", client);
            session.setAttribute("clientName", name);
        }
        /**
         * check if User is Banned
         */
        if (client.getStatus().equals("BANNED")) {
            logger.info(String.format("Client banned:%s", client));
            session.setAttribute("role", 0);
            session.setAttribute("client", null);
            request.setAttribute("error", "user is BANNED");
            return "redirect:/login.jsp";
        }
        /**
         * All checks passed, send to Page
         */
        if ((int) session.getAttribute("role") == 3) {
            return "/welcomeAdmin";
        }
        if ((int) session.getAttribute("role") == 2) {
            return "/welcomeManager";
        }
        return "/menu";
    }
}
