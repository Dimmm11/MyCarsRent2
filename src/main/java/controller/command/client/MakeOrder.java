package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.entity.Car;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MakeOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String driver = request.getParameter(Const.DRIVER);
        int term = Integer.parseInt(request.getParameter(Const.TERM));
        Client client = (Client)session.getAttribute(Const.CLIENT);
        try(JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao();
            JDBCOrderDao orderDao = (JDBCOrderDao)JDBCDaoFactory.getInstance().createOrderDao()){
            Car car = carDao.getCarById(Integer.parseInt(request.getParameter(Const.ID)));
            request.setAttribute(Const.CAR, car);
            request.setAttribute(Const.DRIVER, driver);
            request.setAttribute(Const.TERM, term);
            orderDao.carOrder(car, client, driver, term);
        }catch (Exception e){
             return "redirect:/error404.html";
        }
        return "/profile";
    }
}
