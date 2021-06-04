package model.DAO.impl;

import model.entity.Client;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.logging.log4j.core.config.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
public class JDBCClientDaoTest {
    Connection con;
    JDBCClientDao jdbcClientDao;

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
        jdbcClientDao = new JDBCClientDao(con);
    }

    @Test
    public void testGetClients(){
        List<Client> clients = jdbcClientDao.getClients();
        assertEquals("Olya",clients.get(0).getLogin());
    }

    @Test
    public void testGetClient(){
        Client client = jdbcClientDao.getClient("Olya");
        assertEquals(3, client.getId());
    }

    @Test
    public void testGetStaff(){
        List<Client> staff = jdbcClientDao.getStaff();
        assertEquals(2,staff.size());
    }

    @Test
    public void testMakeManager(){
       assertTrue(jdbcClientDao.makeManager("Olya"));
    }

    @Test
    public void testRemoveManager(){
        assertTrue(jdbcClientDao.removeManager("Olya"));
    }

    @Test
    public void testRegister(){
        Client client = new Client();
        client.setLogin("Sasha");
        client.setPassword("Qwerty");
        client.setPassport("QQ123");
        assertTrue(jdbcClientDao.register(client));
    }

    @Test
    public void testBan(){
        assertTrue(jdbcClientDao.ban("Olya"));
    }

    @Test
    public void testUnBan() throws SQLException {
        assertTrue(jdbcClientDao.unBan("Olya"));
    }

}
