package controller.command.manager;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class WelcomeManager implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        /**
         * to Manager menu
         */
        return "/WEB-INF/manager/welcomeManager.jsp";
    }
}
