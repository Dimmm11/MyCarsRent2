package model.DAO.impl;

import model.entity.Car;
import model.entity.Client;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class JDBCCarDaoTest {

    Connection con;
    JDBCCarDao jdbcCarDao;

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
    public void jdbcCreate() throws SQLException {
        con = DBConnector.getDataSource().getConnection();
        jdbcCarDao = new JDBCCarDao(con);
    }

    @Test
    public void testGetCarById() {
        Car car = jdbcCarDao.getCarById(1);
        assertNotNull(car);
    }

    @Test
    public void testGetCarsByClass(){
        List<Car> cars = jdbcCarDao.getCarsByClass("econom","price", "ASC");
        assertEquals(1, cars.size());
    }

    @Test
    public void testGetCarsByMarque(){
        List<Car> cars = jdbcCarDao.getCarsByMarque("Skoda", "price", "ASC");
        assertEquals(1,cars.size());
    }

    @Test
    public void testGetCarsByClient(){
        Client client = new Client();
        client.setId(3);
        client.setLogin("Olya");
        List<Car> orderedCars = jdbcCarDao.getCarsByClient(client, 0, 10);
        assertEquals(2, orderedCars.size());
    }

    @Test
    public void testGetAllCars(){
        List<Car> allCars = jdbcCarDao.getAllCars("id", "ASC");
        assertEquals(3, allCars.size());
    }

    @Test
    public void testGetOrderedCars(){
        List<Car> orderedCars = jdbcCarDao.getOrderedCars(0, 10);
        assertEquals(2, orderedCars.size());
    }

    @Test
    public void testAddCar(){
        Car car = new Car();
        car.setMarque("testMarque");
        car.setModel("testModel");
        car.setClazz("econom");
        car.setPrice(BigDecimal.valueOf(50));
        assertTrue(jdbcCarDao.addCar(car));
    }

    @Test
    public void testUpdatePrice() throws SQLException {
        Car car = jdbcCarDao.getCarById(1);
        con = DBConnector.getDataSource().getConnection();
        jdbcCarDao = new JDBCCarDao(con);
        assertTrue(jdbcCarDao.updatePrice(BigDecimal.valueOf(44),car));
    }

    @Test
    public void testDeleteCar() throws SQLException {
        Car car = new Car();
        car.setMarque("test");
        car.setModel("test");
        car.setClazz("econom");
        car.setPrice(BigDecimal.valueOf(50));
        jdbcCarDao.addCar(car);
        con = DBConnector.getDataSource().getConnection();
        jdbcCarDao = new JDBCCarDao(con);
        assertTrue(jdbcCarDao.deleteCar(4));
    }

}
