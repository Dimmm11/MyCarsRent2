package controller.command.manager;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.ClientDAO;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ManagerClients implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        List<Client> clients = new ArrayList<>();
//        clients = ClientDAO.getClients();
//        request.setAttribute("adminClients", clients); // проверить атрибуты админа-менеджера тут
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        /**
         *  ???????????????????  после 9го обращения виснет
         */
//        JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao();
//        List<Client> allClients = clientDao.getClients();

        List<Client> allClients = ClientDAO.getClients();
        List<Client> clients = ClientDAO.getClients((page - 1) * 3, 3);
        int numPages = new PageCalculator().getNumPages(allClients.size());

        request.setAttribute("page", page);
        request.setAttribute("numPages", numPages);
        request.setAttribute("adminClients", clients);

        return "/WEB-INF/manager/managerClients.jsp";
    }
}
