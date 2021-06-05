package controller.command;


import model.DAO.service.ClientService;
import model.entity.Client;
import model.util.CheckClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Login command
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("LoginCommand.execute...");
        HttpSession session = request.getSession();
        String name = request.getParameter("Login");
        String pass = request.getParameter("Password");


        if (name == null || name.equals("") || pass == null || pass.equals("")) {
            session.setAttribute("client", null);
            name = null;
            session.setAttribute("loginError", "All fields required");
            return "redirect:/login.jsp";
        }
        /**
         * check if such client is already Logged in
         */
        if (CommandUtility.checkUserIsLogged(request, name)) {
            logger.info("Repeated access attempt by: " + name);
            request.setAttribute("error", "try again later");
            return "redirect:/index.jsp";
        }
        logger.info(String.format("Logged users: %s", request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers")));
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
        Optional<Client> clientOptional = Optional.ofNullable(client);
        if (!clientOptional.isPresent()) {
            client = new ClientService().getClient(name);
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
        CommandUtility.setClientInContext(request, client.getLogin());
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
