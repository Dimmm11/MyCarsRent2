package model.DAO;

import model.connection.ConnectionPoolHolder;
import model.entity.Car;
import model.entity.Client;
import model.mapper.CarMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public static List<Car> getCarsByClass(String carClass) {
        List<Car> carsByClass = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = SqlQuarry.CARS_BY_CLASS.replace("carclass", carClass);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClass.add(car);
            }
        } catch (SQLException|NullPointerException e) {
//            System.out.println("Failed to show cars by class");
//            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return carsByClass;
    }

    public static List<Car> getCarsByMarque(String marque) {
        List<Car> carsByMarque = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = SqlQuarry.CARS_BY_MARQUE.replaceAll("carmarque", marque);
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

    public static synchronized List<Car> getCarsByClient(Client client) {
        List<Car> carsByClient = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = SqlQuarry.CARS_BY_CLIENT.replaceAll("clientid", String.valueOf(client.getId()));
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

    public static List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(SqlQuarry.ALLCARS);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    public static List<Car> getOrderedCars() {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(SqlQuarry.ORDERED_CARS);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    public static synchronized boolean addCar(Car car) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.ADD_CAR);

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

    public static synchronized boolean updatePrice(double price, Car car) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.PRICE_UPDATE);

            st.setDouble(1, price);
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

    public static synchronized Car getCarById(int id) {
        Car car = null;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.GET_CAR_BY_ID);
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

    public static synchronized boolean deleteCar(int id) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.DELETE_CAR);
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
}
