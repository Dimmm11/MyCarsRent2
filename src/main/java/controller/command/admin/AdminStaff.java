package controller.command.admin;

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
import java.util.ArrayList;
import java.util.List;

/**
 * gets Staff from DB and returns
 * result by portions, depending on
 * amount of needed pages
 */
public class AdminStaff implements Command {
    private static final Logger logger = LogManager.getLogger(AdminStaff.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("AdminStaff...");
        int page = 1;
        if (request.getParameter(Const.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Const.PAGE));
        }
        List<Client> allStaff = new ClientService().getStaff();
        logger.info("allStaff {}", allStaff);
        List<Client> staff = new ClientService().getStaff((page - 1) * 3, 3);
        logger.info("staff on page {}", staff);
        int numPages = new PageCalculator().getNumPages(allStaff.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.STAFF, staff);
        return "WEB-INF/admin/adminStaff.jsp";
    }
}
