package controller.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

class CommandUtility {
    /**
     * set client to Context for checking
     * if such Account is Logged in
     */
    static void setClientInContext(HttpServletRequest request, String name) {
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", name);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
