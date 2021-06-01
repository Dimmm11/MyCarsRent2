package controller.command.manager;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.util.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int page = 1;
        Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
        if (pageOptional.isPresent()) {
            page = Integer.parseInt(pageOptional.get());
        }
        Optional<String> columnOptional = Optional.ofNullable((String) session.getAttribute(Const.COLUMN));
        String column = columnOptional.orElse(Const.ID);
        Optional<String> sortOptional = Optional.ofNullable((String) session.getAttribute(Const.SORT_ORDER));
        String sortOrder = sortOptional.orElse(Const.ASC);
        Optional<String> colStringOptional = Optional.ofNullable(request.getParameter(Const.COLUMN));
        String columnAttibute = colStringOptional.orElse(column);
        session.setAttribute(Const.COLUMN, columnAttibute);
        Optional<String> sortStringOpt = Optional.ofNullable(request.getParameter(Const.SORT_ORDER));
        String sortAttribute = sortStringOpt.orElse(sortOrder);
        session.setAttribute(Const.SORT_ORDER, sortAttribute);
        List<Car> allCars = new ArrayList<>();
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()) {
            allCars = carDao.getAllCars((String) session.getAttribute(Const.COLUMN),
                    (String) session.getAttribute(Const.SORT_ORDER));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Car> cars = new Paginator<Car>().getEntitiesForPage(allCars,
                (page - 1) * 3,
                (page - 1) * 3 + 3);
        int numPages = new PageCalculator().getNumPages(allCars.size());
        request.setAttribute(Const.PAGE, page);
        request.setAttribute(Const.NUM_PAGES, numPages);
        request.setAttribute(Const.ALLCARS, cars);
        return "/WEB-INF/manager/managerCars.jsp";

    }

}
