package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class AddCarButton implements Command {
    @Override
    public String execute(HttpServletRequest request) {




        return "WEB-INF/admin/carAdd.jsp";
    }
}
