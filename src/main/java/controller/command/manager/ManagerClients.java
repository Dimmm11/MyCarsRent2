package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;
import model.util.pagination.PageCalculator;
import model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
/**
 * Command check DB for clients and
 * showing for manager(or Admin)
 */
public class ManagerClients implements Command {
    private static final Logger logger = LogManager.getLogger(ManagerClients.class.getName());
    @Override
    public String execute(HttpServletRequest request) {
        logger.info("ManagerClients...");
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        List<Client> allClients = new ClientService().getClients();
        logger.info("allClients: "+allClients);
        List<Client> clients = new ClientService().getClients((page - 1) * 3, 3);
        logger.info("clients: "+clients);
        int numPages = new PageCalculator().getNumPages(allClients.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ADMIN_CLIENTS, clients);
        return "/WEB-INF/manager/managerClients.jsp";
    }
}
