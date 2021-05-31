package model.DAO;

import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.impl.JDBCOrderDao;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main{
    public static void main(String[] args) {
//        JDBCCarDao carDao = (JDBCCarDao) new JDBCDaoFactory().createCarDao();
//        JDBCCarDao carDao = (JDBCCarDao)JDBCDaoFactory.getInstance().createCarDao();
//        List<Car> cars = carDao.getAllCars();
//        for (Car car : cars) {
//            System.out.println(car);
//        }



    }

    static List<Car> sortedCars(List<Car> allCars,int startIndex, int lastIndex){
        List<Car> sortedCars = new ArrayList<>();
        if(allCars.size()-1 < lastIndex){
            lastIndex = allCars.size()-1;
        }
        sortedCars = allCars.subList(startIndex, lastIndex);

        return sortedCars;
    }


}
