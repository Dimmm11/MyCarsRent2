package controller.command.admin;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * remove car from DB
 */
public class AdminDeleteCar implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int carId = Integer.parseInt(request.getParameter(Const.CAR_ID));
        try(JDBCCarDao carDao =(JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()){
            carDao.deleteCar(carId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "managerCars";
    }
}
