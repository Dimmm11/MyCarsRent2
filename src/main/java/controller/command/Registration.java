package controller.command;


import model.DAO.service.ClientService;
import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Registration.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Registration...");
        HttpSession session = request.getSession();
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        String passport = request.getParameter("Passport");
        /**
         * empty fields check
         */
        if (login == null || login.equals("")
                || password == null || password.equals("")
                || passport == null || passport.equals("")) {
            session.setAttribute("loginError", "All fields required");
            return "redirect:/register.jsp";
        }
        /**
         * matching regex check
         */
        Matcher loginMatch = Pattern.compile("[A-Za-z\\u0406-\\u04570-9]{2,12}").matcher(login);
        Matcher passwordMatch = Pattern.compile("[A-Za-z\\u0406-\\u04570-9]{2,12}").matcher(password);
        if (!loginMatch.matches() || !passwordMatch.matches()) {
            session.setAttribute("loginError", "Required sequence of letters & digits from 2 to 12 symbols");
            return "redirect:/register.jsp";
        }
        Client client = new Client(login, password, passport);
        logger.info(String.format("Client: %s, %s, %s", login, passport, passport));
        /**
         * If registration successful - send to Page, else - print error
         */
        if (new ClientService().register(client)) {
            client = new ClientService().getClient(request.getParameter("Login"));
            session.setAttribute("clientName", login);
            session.setAttribute("client", client);
            session.setAttribute("role", client.getRole_id());
            logger.info("Registered client: {}", client);
            return "/menu";
        }
        logger.info(String.format("Such user exists%s", login));
        session.setAttribute("loginError", "Such user exists");
        return "redirect:/register.jsp";
    }
}
