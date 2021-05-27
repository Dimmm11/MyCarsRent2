package controller.command;

import javax.servlet.http.HttpServletRequest;

public class Menu implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/client/menu.jsp";
    }
}
