package controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * client menu
 */
public class Menu implements Command {
    private static final Logger logger = LogManager.getLogger(Menu.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("Menu...");
        return "/WEB-INF/client/menu.jsp";
    }
}
