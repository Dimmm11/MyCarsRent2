package controller.command;

import model.DAO.CarDAO;
import model.entity.Car;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Currency currency = Currency.getInstance(new Locale("uk","UA"));
       BigDecimal b = new BigDecimal("10.2");

       Car car = CarDAO.getCarById(1);
       BigDecimal a = car.getPrice();

        System.out.println(a.multiply(BigDecimal.valueOf(5))+" "+currency.getCurrencyCode());

    }
}
