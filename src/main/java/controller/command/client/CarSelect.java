package controller.command.client;

import controller.command.Command;
import controller.command.service.PageCalculator;
import model.DAO.CarDAO;
import model.entity.Car;
import model.entity.Client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CarSelect implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Car> cars;

        String marque = request.getParameter("marque");
        if (marque != null) {
//            cars = CarDAO.getCarsByMarque(request.getParameter("marque"));
//            request.setAttribute("carsByMarque", cars);
//
//            Client client = (Client) session.getAttribute("client");
//            System.out.println("client ID in SESSION = "+client.getId());
//            request.setAttribute("marque",request.getParameter("marque"));

            List<Car> allCars = CarDAO.getCarsByMarque(marque);
            int page=1;
            if(request.getParameter("page")!=null){
                page = Integer.parseInt(request.getParameter("page"));
            }
            cars = CarDAO.getCarsByMarque(marque,(page-1)*3, 3);

            int numPages = new PageCalculator().getNumPages(allCars.size());
            request.setAttribute("page",page);
            request.setAttribute("numPages",numPages);
            request.setAttribute("carsByMarque", cars);
            request.setAttribute("marque", marque);

            return "/WEB-INF/client/carsByMarque.jsp";
        }else {
            try{
//                cars = CarDAO.getCarsByClass(request.getParameter("car_class"));
//                request.setAttribute("marque",request.getParameter("marque"));
//                request.setAttribute("carsByClass", cars);
                String car_class = request.getParameter("car_class");
                List<Car> allCars = CarDAO.getCarsByClass(car_class);

                System.out.println("I am in CarSelect ELSE");
                int page = 1;
                if(request.getParameter("page")!=null){
                    page = Integer.parseInt(request.getParameter("page"));
                }
                System.out.println("Page: "+page);
                cars = CarDAO.getCarsByClass(car_class, (page-1)*3, 3);
                System.out.println("cars list size: "+cars.size());
                int numPages = new PageCalculator().getNumPages(allCars.size());
                request.setAttribute("page",page);
                request.setAttribute("numPages",numPages);
                request.setAttribute("carsByClass", cars);
                request.setAttribute("car_class", car_class);

                return "/WEB-INF/client/carsByClass.jsp";
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                request.setAttribute("error", e.getMessage());
                return "index.jsp";
            }

        }

    }
//    private static int getNumPages(int listSize){
//        int res=0;
//        if(listSize%3==0){
//            res=listSize/3;
//        }else {
//            res = listSize/3+1;
//        }
//        System.out.println("RES in method getNumPages: "+res);
//        return res;
//    }
}
