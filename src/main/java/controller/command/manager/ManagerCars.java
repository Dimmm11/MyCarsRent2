package controller.command.manager;

import controller.command.Command;
import model.DAO.CarDAO;
import model.entity.Car;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ManagerCars implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Car> allCars = CarDAO.getAllCars();
        List<Car> cars =CarDAO.getAllCars((page-1)*3, 3);
        int numPages = getNumPages(allCars.size());

        request.setAttribute("page",page);
        request.setAttribute("numPages",numPages);
        request.setAttribute("allCars", cars);
        return "/WEB-INF/manager/managerCars.jsp";

    }
    private static int getNumPages(int listSize){
        int res=0;
        if(listSize%3==0){
            res=listSize/3;
        }else {
            res = listSize/3+1;
        }
        System.out.println("RES in method getNumPages: "+res);
        return res;
    }
}
