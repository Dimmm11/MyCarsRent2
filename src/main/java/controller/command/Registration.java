package controller.command;

import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
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
        /**
         * If registration successful - send to Page, else - print error
         */
        try (JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao()) {
            if (clientDao.register(client)) {
                client = clientDao.getClient(request.getParameter("Login"));
                session.setAttribute("client", client);
                session.setAttribute("role", client.getRole_id());
                return "/WEB-INF/client/menu.jsp";
            }
        } catch (Exception e) {
            session.setAttribute("loginError", "Such user exists");
            return "redirect:/register.jsp";
        }
        return "register.jsp";
    }
}
