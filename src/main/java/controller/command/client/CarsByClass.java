package controller.command.client;

import controller.command.Command;
import controller.constants.Const;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.service.CarService;
import model.util.pagination.PageCalculator;
import model.entity.Car;
import model.util.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class CarsByClass implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
            int page = 1;
            Optional<String> optionalPage = Optional.ofNullable(request.getParameter(Const.PAGE));
            if (optionalPage.isPresent()) {
                page = Integer.parseInt(optionalPage.get());
            }
            String carClass = request.getParameter(Const.CAR_CLASS);
            Optional<String> carClassOpt = Optional.ofNullable(request.getParameter(Const.CAR_CLASS));
            if(!carClassOpt.isPresent()){
                carClass = (String)session.getAttribute(Const.CAR_CLASS);
            }
            session.setAttribute(Const.CAR_CLASS, carClass);
            String column = (String) session.getAttribute(Const.COLUMN);
            Optional<String> columnOpt = Optional.ofNullable((String)session.getAttribute(Const.COLUMN));
            if(!columnOpt.isPresent()){
                column = Const.ID;
            }
            String sortOrder = (String) session.getAttribute(Const.SORT_ORDER);
            Optional<String> sortOpt = Optional.ofNullable((String) session.getAttribute(Const.SORT_ORDER));
            if(!sortOpt.isPresent()){
                sortOrder = Const.ASC;
            }
            Optional<String> stringOptional = Optional.ofNullable(request.getParameter(Const.COLUMN));
            String column1 = stringOptional
                    .orElse(column);
            session.setAttribute(Const.COLUMN, column1);
            Optional<String> orderOptional = Optional.ofNullable(request.getParameter(Const.SORT_ORDER));
            String orderSort = orderOptional
                    .orElse(sortOrder);
            session.setAttribute(Const.SORT_ORDER,orderSort);
            List<Car> allCars = new CarService().getCarsByClass(
                    (String) session.getAttribute(Const.CAR_CLASS),
                    (String) session.getAttribute(Const.COLUMN),
                    (String) session.getAttribute(Const.SORT_ORDER));
            List<Car> cars = new Paginator<Car>().getEntitiesForPage(allCars,
                    (page - 1) * 3,
                    (page - 1) * 3 + 3);
            int numPages = new PageCalculator().getNumPages(allCars.size());
            request.setAttribute(Const.PAGE, page);
            request.setAttribute(Const.NUM_PAGES, numPages);
            request.setAttribute(Const.CARS_BY_CLASS, cars);
            request.setAttribute(Const.CAR_CLASS, carClass);
        return "/WEB-INF/client/carsByClass.jsp";
    }
}
