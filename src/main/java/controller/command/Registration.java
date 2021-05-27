package controller.command;

import controller.command.Command;
import model.DAO.ClientDAO;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        /**
         * делаем Клиента из параметров авторизации
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
            if(ClientDAO.register(client)) {
                System.out.println("reg success");
                return "/WEB-INF/client/menu.jsp";
            }
        }catch (RuntimeException e){
            System.out.println("Failed reg");
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
