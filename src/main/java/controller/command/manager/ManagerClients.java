package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.util.pagination.PageCalculator;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerClients implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter(Const.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Const.PAGE));
        }
        try (JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao()) {
            List<Client> allClients = clientDao.getClients();
            List<Client> clients = clientDao.getClients((page - 1) * 3, 3);
            int numPages = new PageCalculator().getNumPages(allClients.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.ADMIN_CLIENTS, clients);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/WEB-INF/manager/managerClients.jsp";
    }
}
