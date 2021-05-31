package controller.command.admin;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * delete Client from DB
 */
public class DeleteClient implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try (JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao()) {
            clientDao.deleteClient(request.getParameter(Const.LOGIN));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "managerClients";
    }
}
