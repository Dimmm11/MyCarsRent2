package model.DAO.service;

import model.DAO.DaoFactory;
import model.DAO.impl.DBConnector;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCClientDao;
import model.DAO.impl.JDBCOrderDao;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    Connection con;
    @Mock
    Connection con2;
    @Mock
    JDBCOrderDao jdbcOrderDao;
    @Mock
    JDBCOrderDao jdbcOrderDao2;
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
        jdbcOrderDao = new JDBCOrderDao(con);
        jdbcOrderDao2 = new JDBCOrderDao(con2);
    }

    @Test
    public void testGetOrdersByClient(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        Client client = new Client();
        client.setLogin("Olya");
        client.setId(3);
        assertTrue(orderService.getOrdersByClient(client).size()>0);
    }

    @Test
    public void testCancelOrder(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        assertTrue(orderService.cancelOrder(2));
    }

    @Test
    public void testCarOrder(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        Car car = new Car();
        car.setPrice(BigDecimal.valueOf(1));
        car.setMarque("test");
        car.setModel("test");
        car.setClazz("econom");
        car.setId(4);
        Client client = new Client();
        client.setLogin("Olya");
        client.setId(3);
        JDBCCarDao carDao = new JDBCCarDao(con2);
        carDao.addCar(car);
        assertTrue(orderService.carOrder(car,client,"YES", 1));
    }

    @Test
    public void testCarReturn(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        assertTrue(orderService.carReturn(1,3));
    }

    @Test
    public void testGetAllOrders(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        assertTrue(orderService.getAllOrders().size()>0);
    }

    @Test
    public void testSetPenalty(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        assertTrue(orderService.setPenalty(3, BigDecimal.valueOf(50)));
    }

    @Test
    public void testSetOrderStatus(){
        when(daoFactory.createOrderDao()).thenReturn(jdbcOrderDao);
        OrderService orderService = new OrderService(daoFactory);
        assertTrue(orderService.setOrderStatus(2, "REJECTED"));
    }
}
