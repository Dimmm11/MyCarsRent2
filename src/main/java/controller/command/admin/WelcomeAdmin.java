package controller.command.admin;

import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Admin page command
 */
public class WelcomeAdmin implements Command {
    private static final Logger logger = LogManager.getLogger(WelcomeAdmin.class.getName());
        @Override
        public String execute(HttpServletRequest request) {
            logger.info(String.format("welcome admin: %s", request.getSession().getAttribute("client")));
            return "/WEB-INF/admin/welcomeAdmin.jsp";
        }
}
