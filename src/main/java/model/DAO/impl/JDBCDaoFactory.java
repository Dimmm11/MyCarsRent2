package model.DAO.impl;

import model.DAO.CarDAO;
import model.DAO.ClientDAO;
import model.DAO.DaoFactory;
import model.DAO.OrderDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarDAO createCarDao() {
        return new JDBCCarDao(getConnection());
    }

    @Override
    public ClientDAO createClientDao() {
        return new JDBCClientDao(getConnection());
    }

    @Override
    public OrderDAO createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }


}
