package model.DAO;

import model.entity.Car;
import model.entity.Client;

import java.math.BigDecimal;
import java.util.List;

public interface CarDAO extends GenericDao<Car>{
    List<Car> getCarsByClass(String carClass, String column, String sortingOrder);

    List<Car> getCarsByMarque(String marque, String column, String sortingOrder);

    List<Car> getCarsByClient(Client client, int index, int offset);

    List<Car> getAllCars(String column, String sortingOrder);

    List<Car> getOrderedCars(int index, int offset);

    boolean addCar(Car car);

    boolean updatePrice(BigDecimal price, Car car);

    Car getCarById(int id);

    boolean deleteCar(int id);
}
