package model.DAO.impl;

import model.util.Sql;
import model.DAO.mapper.CarMapper;
import model.DAO.CarDAO;
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

    @Override
    public List<Car> getCarsByClass(String carClass, String column, String sortingOrder) {
        List<Car> carsByClass = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            String sql = Sql.CARS_BY_CLASS.replace("carclass", carClass).replace(";",
                    " ORDER BY " + column + " " + sortingOrder + " ;");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClass.add(car);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return carsByClass;
    }

    @Override
    public List<Car> getCarsByMarque(String marque, String column, String sortingOrder) {
        List<Car> carsByMarque = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            String sql = Sql.CARS_BY_MARQUE.replaceAll("carmarque", marque).replace(";",
                    " ORDER BY " + column + " " + sortingOrder + " ;");
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

    @Override
    public List<Car> getCarsByClient(Client client, int index, int offset) {
        List<Car> carsByClient = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = connection) {
            con.setAutoCommit(false);
            pst = con.prepareStatement(Sql.PAGE_CARS_BY_CLIENT);
            pst.setInt(1, client.getId());
            pst.setInt(2, index);
            pst.setInt(3, offset);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                carsByClient.add(car);
            }
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carsByClient;
    }

    @Override
    public List<Car> getAllCars(String column, String sortingOrder) {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            String ss = Sql.ALLCARS.replaceAll(";", " ORDER BY " + column + " " + sortingOrder + " ;");
            ResultSet rs = st.executeQuery(ss);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public List<Car> getOrderedCars(int index, int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = connection) {
            pst = con.prepareStatement(Sql.PAGE_ORDERED_CARS);
            pst.setInt(1, index);
            pst.setInt(2, offset);
            rs = pst.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return allCars;
    }

    @Override
    public boolean addCar(Car car) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.ADD_CAR);
            st.setString(1, car.getMarque());
            st.setString(2, car.getClazz());
            st.setString(3, car.getModel());
            st.setBigDecimal(4, car.getPrice());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean updatePrice(BigDecimal price, Car car) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.PRICE_UPDATE);
            st.setBigDecimal(1, price);
            st.setInt(2, car.getId());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.GET_CAR_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            car = new CarMapper().mapFromResultSet(rs);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return car;
    }

    @Override
    public boolean deleteCar(int id) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.DELETE_CAR);
            st.setInt(1, id);

            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
