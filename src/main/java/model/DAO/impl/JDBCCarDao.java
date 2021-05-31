package model.DAO.impl;

import model.DAO.Sql;
import model.DAO.mapper.CarMapper;
import model.DAO.tryService.CarDAO;
import model.connection.ConnectionPoolHolder;
import model.entity.Car;
import model.entity.Client;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarDao implements CarDAO {
    private Connection connection;

    public JDBCCarDao(Connection connection) {
        this.connection = connection;
    }

    // =================================================
    public List<Car> getCarsByClass(String carClass) {
        List<Car> carsByClass = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = Sql.CARS_BY_CLASS.replace("carclass", carClass);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClass.add(car);
            }
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
        return carsByClass;
    }

    public List<Car> getCarsByClass(String carClass, int index, int offset) {
        List<Car> carsByClass = new ArrayList<>();
        PreparedStatement stt = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            stt = con.prepareStatement(Sql.DYNAMIC_CAR_CLASS);
            stt.setString(1, carClass);
            stt.setInt(2, index);
            stt.setInt(3, offset);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClass.add(car);
            }
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("list size in CarDAO.getCarsByClass: " + carsByClass.size());
        return carsByClass;
    }

    public List<Car> getCarsByMarque(String marque) {
        List<Car> carsByMarque = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = Sql.CARS_BY_MARQUE.replaceAll("carmarque", marque);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByMarque.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsByMarque;
    }

    public List<Car> getCarsByMarque(String marque, int index, int offset) {
        List<Car> carsByMarque = new ArrayList<>();
        PreparedStatement stt = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            stt = con.prepareStatement(Sql.DYNAMIC_CAR_MARQUE);
            stt.setString(1, marque);
            stt.setInt(2, index);
            stt.setInt(3, offset);
            ResultSet rs = stt.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByMarque.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsByMarque;
    }

    public List<Car> getCarsByClient(Client client) {
        List<Car> carsByClient = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = Sql.CARS_BY_CLIENT.replaceAll("clientid", String.valueOf(client.getId()));
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClient.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsByClient;
    }

    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();
        ResultSet rs=null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            rs = st.executeQuery(Sql.ALLCARS);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allCars;
    }

    public List<Car> getAllCars(int index, int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            st = con.prepareStatement(Sql.PAGE_ALLCARS);
            st.setInt(1, index);
            st.setInt(2, offset);
            rs = st.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("connect closed in JDBCCarDAO.getAllCars");
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allCars;
    }

    public List<Car> getOrderedCars() {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ORDERED_CARS);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    public boolean addCar(Car car) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.ADD_CAR);

            st.setString(1, car.getMarque());
            st.setString(2, car.getClazz());
            st.setString(3, car.getModel());
            st.setBigDecimal(4, car.getPrice());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to add a car");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean updatePrice(BigDecimal price, Car car) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.PRICE_UPDATE);

            st.setBigDecimal(1, price);
            st.setInt(2, car.getId());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to UPDATE PRICE");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Car getCarById(int id) {
        Car car = null;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.GET_CAR_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            car = new CarMapper().mapFromResultSet(rs);
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to UPDATE PRICE");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return car;
    }

    public boolean deleteCar(int id) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.DELETE_CAR);
            st.setInt(1, id);

            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to delete car");
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // =================================================
    @Override
    public boolean create(Car car) {
        return false;
    }

    @Override
    public Car findById(int id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
