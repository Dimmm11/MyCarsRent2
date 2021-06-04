package controller.command.manager;

import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Manager page command
 */
public class WelcomeManager implements Command {
    private static final Logger logger = LogManager.getLogger(WelcomeManager.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("WelcomeManager...");
        /**
         * to Manager menu
         */
        return "/WEB-INF/manager/welcomeManager.jsp";
    }
}
