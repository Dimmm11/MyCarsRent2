package controller.command.manager;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * ban Client
 */
public class Ban implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        try(JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao()){
            clientDao.ban(request.getParameter(Const.LOGIN));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "managerClients";
    }
}
