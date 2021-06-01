package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * set Rights to user, depending
 * on request parameter
 */
public class StaffRights implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String adminAction = request.getParameter(Const.ADMIN_ACTION);
        if (adminAction.equals(Const.REMOVE_MANAGER)) {
            try (JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao();) {
                clientDao.removeManager(request.getParameter(Const.LOGIN));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "adminStaff";
        } else {
            try (JDBCClientDao clientDao = (JDBCClientDao) JDBCDaoFactory.getInstance().createClientDao();) {
                clientDao.makeManager(request.getParameter(Const.LOGIN));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "managerClients";
        }
    }
}
