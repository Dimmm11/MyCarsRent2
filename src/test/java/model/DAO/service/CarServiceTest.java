package model.DAO.service;

import controller.constants.Const;
import model.DAO.DaoFactory;
import model.DAO.impl.*;
import model.entity.Car;
import model.entity.Client;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class CarServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    JDBCCarDao jdbcCarDao;

    @Mock
    Connection con;
    @Mock
    Connection con2;

    @Mock
    JDBCCarDao jdbcCarDao2;

    @Mock
    DaoFactory daoFactory;

    @BeforeClass
    public static void dbCreate() throws SQLException, IOException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306?serverTimezone=EET";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/main/resources/db-test.sql"));
        sr.runScript(reader);
        reader.close();
    }

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        con = DBConnector.getDataSource().getConnection();
        con2 = DBConnector.getDataSource().getConnection();
        jdbcCarDao = new JDBCCarDao(con);
        jdbcCarDao2 = new JDBCCarDao(con2);
    }

    @Test
    public void testGetCarById() throws SQLException {
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        Car result = carService.getCarById(1);
        assertEquals("Suzuki", result.getMarque());
    }

    @Test
    public void testAddCar(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        Car car = new Car();
        car.setPrice(BigDecimal.valueOf(1));
        car.setMarque("test");
        car.setModel("test");
        car.setClazz("econom");
        assertTrue(carService.addCar(car));
    }

    @Test
    public void testDeleteCar(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        assertTrue(carService.deleteCar(2));
    }

    @Test
    public void testUpdatePrice(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        Car car = jdbcCarDao2.getCarById(1);
        assertTrue(carService.updatePrice(BigDecimal.valueOf(100), car));
    }

    @Test
    public void testGetCarsByClass(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        List<Car> cars = carService.getCarsByClass("econom", Const.ID, Const.ASC);
        assertTrue(cars.size()>0);
    }

    @Test
    public void testGetCarsByMarque(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        List<Car> cars = carService.getCarsByMarque("Suzuki", Const.ID, Const.ASC);
        assertEquals(0,cars.size());
    }

    @Test
    public void testGetCarsByClient(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        JDBCClientDao clientDao = new JDBCClientDao(con2);
        Client client = clientDao.getClient("Olya");
        CarService carService = new CarService(daoFactory);
        List<Car> cars = carService.getCarsByClient(client,0,3);
        assertEquals(2, cars.size());
    }

    @Test
    public void testGetAllCars(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        List<Car> cars = carService.getAllCars(Const.ID, Const.ASC);
        assertTrue(cars.size()>0);
    }

    @Test
    public void testGetOrderedCars(){
        when(daoFactory.createCarDao()).thenReturn(jdbcCarDao);
        CarService carService = new CarService(daoFactory);
        List<Car> cars = carService.getOrderedCars(0, 3);
        assertTrue(cars.size()>0);
    }

}
