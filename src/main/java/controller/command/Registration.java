package controller.command;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        /**
         * client from registration
         */
        Client client = new Client(
                request.getParameter("Login"),
                request.getParameter("Password"),
                request.getParameter("Passport")
        );
        /**
         * If registration successful - send to Page
         */
        try {
            if (ClientDAO.register(client)) {
                client = ClientDAO.getClient(request.getParameter("Login"));
                session.setAttribute("client", client);
                session.setAttribute("role", client.getRole_id());
                return "/WEB-INF/client/menu.jsp";
            }
        } catch (RuntimeException e) {
            request.setAttribute("error", e.getMessage());
            return "register.jsp";
        }
        /**
         * return with Fail-message
         */
        request.setAttribute("fail", "Such login exists");
        return "register.jsp";
    }
}
