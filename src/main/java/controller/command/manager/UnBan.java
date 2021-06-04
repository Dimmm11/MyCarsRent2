package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnBan implements Command {
    private static final Logger logger = LogManager.getLogger(UnBan.class.getName());

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("UnBan client: "+request.getParameter(Const.LOGIN));
        new ClientService().unBan(request.getParameter(Const.LOGIN));
        return "managerClients";
    }
}
