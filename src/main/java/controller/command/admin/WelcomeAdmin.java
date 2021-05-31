package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Admin welcome page
 */
public class WelcomeAdmin implements Command {
        @Override
        public String execute(HttpServletRequest request) {
            return "/WEB-INF/admin/welcomeAdmin.jsp";
        }

}
