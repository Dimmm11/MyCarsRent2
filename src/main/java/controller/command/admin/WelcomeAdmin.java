package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class WelcomeAdmin implements Command {
        @Override
        public String execute(HttpServletRequest request) {
            /**
             * to Admin menu
             */
            return "/WEB-INF/admin/welcomeAdmin.jsp";
        }
}
