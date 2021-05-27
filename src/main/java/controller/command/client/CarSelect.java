package controller.command.client;

import controller.command.Command;
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
            cars = CarDAO.getCarsByMarque(request.getParameter("marque"));
            request.setAttribute("carsByMarque", cars);

            Client client = (Client) session.getAttribute("client");
            System.out.println("client ID in SESSION = "+client.getId());
            request.setAttribute("marque",request.getParameter("marque"));

            return "/WEB-INF/client/carsByMarque.jsp";
        }else {
            try{
                cars = CarDAO.getCarsByClass(request.getParameter("car_class"));
                request.setAttribute("marque",request.getParameter("marque"));
                request.setAttribute("carsByClass", cars);
                return "/WEB-INF/client/carsByClass.jsp";
            }catch (RuntimeException e){
                request.setAttribute("error", e.getMessage());
                return "index.jsp";
            }

        }

    }
}
