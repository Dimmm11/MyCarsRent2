package model.DAO.service;

import model.DAO.DaoFactory;
import model.DAO.impl.ConnectionPoolHolder;
import model.DAO.impl.JDBCCarDao;
import model.DAO.impl.JDBCDaoFactory;
import model.DAO.mapper.CarMapper;
import model.entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.ArgumentMatcher.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class CarServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    JDBCCarDao jdbcCarDao;
    //=========


    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetCarById() throws SQLException {
        Car car = new Car();
        car.setId(1);

        when(carService.getCarById( anyInt() ))
                .thenReturn(car);

        Car result = carService.getCarById(1);

        assertNotNull(result);


    }
}
