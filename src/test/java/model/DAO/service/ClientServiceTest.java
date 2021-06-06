package model.DAO.service;

import model.DAO.DaoFactory;
import model.DAO.impl.DBConnector;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCClientDao;
import model.entity.Client;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.when;

public class ClientServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    Connection con;
    @Mock
    Connection con2;
    @Mock
    JDBCClientDao jdbcClientDao;
    @Mock
    JDBCClientDao jdbcClientDao2;
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
        jdbcClientDao = new JDBCClientDao(con);
        jdbcClientDao2 = new JDBCClientDao(con2);
    }

    @Test
    public void testGetStaff() {
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        List<Client> clientList = clientService.getStaff();
        assertTrue(clientList.size() > 0);
    }

    @Test
    public void testGetClient() {
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        Client client = clientService.getClient("Olya");
        assertEquals(3, client.getId());
    }

    @Test
    public void testRemoveManager(){
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        assertTrue(clientService.removeManager("Vasya"));
    }

    @Test
    public void testMakeManager(){
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        Client client = new Client();
        client.setLogin("test");
        client.setPassport("test");
        client.setPassport("test");
        jdbcClientDao2.register(client);
        assertTrue(clientService.makeManager("test"));
    }

    @Test
    public void testBan(){
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        assertTrue(clientService.ban("Vasya"));
    }

    @Test
    public void testUnBan(){
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        jdbcClientDao2.ban("Olya");
        assertTrue(clientService.unBan("Olya"));
    }

    @Test
    public void testRegister(){
        when(daoFactory.createClientDao()).thenReturn(jdbcClientDao);
        ClientService clientService = new ClientService(daoFactory);
        Client client = new Client();
        client.setLogin("test2");
        client.setPassport("test2");
        client.setPassport("test2");
        assertTrue(clientService.register(client));
    }
}
