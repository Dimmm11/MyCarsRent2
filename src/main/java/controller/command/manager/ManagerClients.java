package controller.command.manager;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.CarDAO;
import model.DAO.ClientDAO;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.entity.Car;
import model.entity.Client;
import model.service.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        //================================== НАЧИНАЮ ТВОРИТЬ ДИЧЬ
//        HttpSession session = request.getSession();
//        String column = (String) session.getAttribute("column");
//        if(column==null){
//            column="id";
//        }
//        String sortOrder = (String) session.getAttribute("sortOrder");
//        if(sortOrder==null){
//            sortOrder="ASC";
//        }
//        if(request.getParameter("column")!=null){
//            session.setAttribute("column", request.getParameter("column"));
//        }else {
//            session.setAttribute("column", column);
//        }
//        if(request.getParameter("sortOrder")!=null){
//            session.setAttribute("sortOrder", request.getParameter("sortOrder"));
//        }else {
//            session.setAttribute("sortOrder", sortOrder);
//        }
//        List<Client> allClients = ClientDAO.getClients((String)session.getAttribute("column"),(String)session.getAttribute("sortOrder"));
////        System.out.println(allCars);
//        List<Client> clients =  new Paginator<Client>().getEntitiesForPage(allClients,(page - 1) * 3, (page - 1) * 3+3);
////        System.out.println(cars);
        // ==========================
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
