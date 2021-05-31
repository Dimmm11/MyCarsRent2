package controller.command.manager;

import controller.command.Command;
import controller.command.client.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.util.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int page = 1;
        if (request.getParameter(Const.PAGE) != null) {
            page = Integer.parseInt(request.getParameter(Const.PAGE));
        }
        //================================== НАЧИНАЮ ТВОРИТЬ
        String column = (String) session.getAttribute(Const.COLUMN);
        if(column==null){
            column=Const.ID;
        }
        String sortOrder = (String) session.getAttribute(Const.SORT_ORDER);
        if(sortOrder==null){
            sortOrder=Const.ASC;
        }
        if(request.getParameter(Const.COLUMN)!=null){
            session.setAttribute(Const.COLUMN, request.getParameter(Const.COLUMN));
        }else {
            session.setAttribute(Const.COLUMN, column);
        }
        if(request.getParameter(Const.SORT_ORDER)!=null){
            session.setAttribute(Const.SORT_ORDER, request.getParameter(Const.SORT_ORDER));
        }else {
            session.setAttribute(Const.SORT_ORDER, sortOrder);
        }
        List<Car> allCars=new ArrayList<>();
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()) {
            allCars = carDao.getAllCars((String) session.getAttribute(Const.COLUMN), (String) session.getAttribute(Const.SORT_ORDER));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Car> cars =  new Paginator<Car>().getEntitiesForPage(allCars,(page - 1) * 3, (page - 1) * 3+3);
        int numPages = new PageCalculator().getNumPages(allCars.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ALLCARS, cars);
        return "/WEB-INF/manager/managerCars.jsp";

    }

}
