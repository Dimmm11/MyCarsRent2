package controller.filters;

import controller.command.LogOutCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

/**
 * Filter to clear user's session
 * after Logout command
 */
public class LogOutFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LogOutFilter.class.getName());
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("LogoutFilter...");
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        ServletContext context = request.getServletContext();
        /**
         * Clear session & remove current client from context
         */
        logger.info("Logout:"+req.getSession().getAttribute("client"));
        String name = (String) session.getAttribute("clientName");
        HashSet<String> loggedUsers = (HashSet<String>) req.getSession().getServletContext()
                .getAttribute("loggedUsers");
        loggedUsers.remove(name);
        context.setAttribute("loggedUsers",loggedUsers);
        session.setAttribute("client", null);
        session.setAttribute("role", 0);
        context.setAttribute("userName", null);
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setDateHeader("Expires", -1);
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
