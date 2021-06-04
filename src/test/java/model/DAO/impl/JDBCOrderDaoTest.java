package model.DAO.impl;

import model.entity.Client;
import model.entity.Order;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
public class JDBCOrderDaoTest {
    Connection con;
    JDBCOrderDao jdbcOrderDao;

    @BeforeClass
    public static void dbCreate() throws SQLException, FileNotFoundException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306?serverTimezone=EET";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "root");
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/main/resources/db-test.sql"));
        sr.runScript(reader);
    }

    @Before
    public void jdbcCreate() throws SQLException {
        con = DBConnector.getDataSource().getConnection();
        jdbcOrderDao = new JDBCOrderDao(con);
    }

    @Test
    public void testGetOrdersByClient(){
        Client client = new Client();
        client.setId(3);
        List<Order> orderList = jdbcOrderDao.getOrdersByClient(client);
        assertEquals(2, orderList.size());
    }

    @Test
    public void testGetAllOrders(){
        List<Order> orderList = jdbcOrderDao.getAllOrders();
        assertTrue(orderList.size()>1);
    }

    @Test
    public void testSetReason(){
        assertTrue(jdbcOrderDao.setReason(1, "test reason"));
    }



}
