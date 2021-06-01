package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.util.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class CarsByMarque implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars;
        try (JDBCCarDao carDao = (JDBCCarDao) JDBCDaoFactory.getInstance().createCarDao()) {
            Optional<String> marqueOptional = Optional.ofNullable(request.getParameter(Const.MARQUE));
            String marque = marqueOptional.orElse((String) session.getAttribute(Const.MARQUE));
            session.setAttribute(Const.MARQUE, marque);
            int page = 1;
            Optional<String> pageOptional = Optional.ofNullable(request.getParameter(Const.PAGE));
            if (pageOptional.isPresent()) {
                page = Integer.parseInt(pageOptional.get());
            }
            String column = (String) session.getAttribute(Const.COLUMN);
            if (column == null) {
                column = Const.ID;
            }
            String sortOrder = (String) session.getAttribute(Const.SORT_ORDER);
            if (sortOrder == null) {
                sortOrder = Const.ASC;
            }
            Optional<String> columnOptional = Optional.ofNullable(request.getParameter(Const.COLUMN));
            String col = columnOptional.orElse(column);
            session.setAttribute(Const.COLUMN, col);
            Optional<String> orderOptional = Optional.ofNullable(request.getParameter(Const.SORT_ORDER));
            String ord = orderOptional.orElse(sortOrder);
            session.setAttribute(Const.SORT_ORDER, ord);
            List<Car> allCars = carDao.getCarsByMarque((String) session.getAttribute(Const.MARQUE),
                    (String) session.getAttribute(Const.COLUMN),
                    (String) session.getAttribute(Const.SORT_ORDER));
            cars = new Paginator<Car>().getEntitiesForPage(allCars,
                    (page - 1) * 3,
                    (page - 1) * 3 + 3);
            int numPages = new PageCalculator().getNumPages(allCars.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.CARS_BY_MARQUE, cars);
            request.setAttribute(Const.MARQUE, marque);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "/WEB-INF/client/carsByMarque.jsp";
    }
}
