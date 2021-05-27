package controller.command;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        /**
         * Clear session
         */
        session.setAttribute("client", null);
        session.setAttribute("role", 0);
        context.setAttribute("client", null);

        return "redirect:/index.jsp";
    }


}
