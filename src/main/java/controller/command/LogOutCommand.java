package controller.command;


import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogOutCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("LogOutCommand...");
        request.getSession().invalidate();
        return "redirect:/index.jsp";
    }
}
