package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;
import model.util.pagination.PageCalculator;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ManagerClients implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        List<Client> allClients = new ClientService().getClients();
        List<Client> clients = new ClientService().getClients((page - 1) * 3, 3);
        int numPages = new PageCalculator().getNumPages(allClients.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ADMIN_CLIENTS, clients);
        return "/WEB-INF/manager/managerClients.jsp";
    }
}
