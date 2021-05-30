package controller.command.client;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.CarDAO;
import model.entity.Car;
import model.service.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CarsByClass implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars;

        String marque = request.getParameter("marque");

        try{
//                cars = CarDAO.getCarsByClass(request.getParameter("car_class"));
//                request.setAttribute("marque",request.getParameter("marque"));
//                request.setAttribute("carsByClass", cars);
            int page = 1;
            if(request.getParameter("page")!=null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            String car_class = request.getParameter("car_class");
            if(session.getAttribute("car_class")==null){
                session.setAttribute("car_class", car_class);
            }
//                List<Car> allCars = CarDAO.getCarsByClass(car_class);
//                cars = CarDAO.getCarsByClass(car_class, (page-1)*3, 3);
            System.out.println("I am in CarSelect ELSE");
            System.out.println("Page: "+page);
            String column = (String) session.getAttribute("column");
            if(column==null){
                column="id";
            }
            String sortOrder = (String) session.getAttribute("sortOrder");
            if(sortOrder==null){
                sortOrder="ASC";
            }
            if(request.getParameter("column")!=null){
                session.setAttribute("column", request.getParameter("column"));
            }else {
                session.setAttribute("column", column);
            }
            if(request.getParameter("sortOrder")!=null){
                session.setAttribute("sortOrder", request.getParameter("sortOrder"));
            }else {
                session.setAttribute("sortOrder", sortOrder);
            }
            List<Car> allCars = CarDAO.getCarsByClass((String) session.getAttribute("car_class"), (String)session.getAttribute("column"),(String)session.getAttribute("sortOrder"));
            System.out.println("allCars.size: "+allCars.size());
            cars = new Paginator<Car>().getEntitiesForPage(allCars, (page-1)*3, (page - 1) * 3+3);

            System.out.println("cars list size: "+cars.size());
            int numPages = new PageCalculator().getNumPages(allCars.size());
            request.setAttribute("page",page);
            request.setAttribute("numPages",numPages);
            request.setAttribute("carsByClass", cars);
            request.setAttribute("car_class", car_class);

            return "/WEB-INF/client/carsByClass.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            return "index.jsp";
        }
    }
}
