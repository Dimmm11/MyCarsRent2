package model.DAO.impl;

import model.util.Sql;
import model.DAO.mapper.CarMapper;
import model.DAO.CarDAO;
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
            throw new RuntimeException(e);
        }
        return carsByClass;
    }

    public List<Car> getCarsByClass(String carClass, String column, String sortingOrder) {
        List<Car> carsByClass = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
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

    public List<Car> getCarsByMarque(String marque, String column, String sortingOrder) {
        List<Car> carsByMarque = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
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

    public List<Car> getCarsByClient(Client client, int index, int offset) {
        List<Car> carsByClient = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
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
        }finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return carsByClient;
    }

    public List<Car> getAllCars() {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ALLCARS);
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    public List<Car> getAllCars(String column, String sortingOrder) {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
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

    public List<Car> getAllCars(List<Car> allCars, int startIndex, int lastIndex) {
        List<Car> sortedCars = new ArrayList<>();
        if (allCars.size() - 1 < lastIndex) {
            lastIndex = startIndex + ((allCars.size()) - startIndex);
        }
        sortedCars = allCars.subList(startIndex, lastIndex);
        return sortedCars;
    }

    public List<Car> getAllCars(int index, int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement st = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            st = con.prepareStatement(Sql.PAGE_ALLCARS);
            st.setInt(1, index);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    public List<Car> getOrderedCars(int index, int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
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
        PreparedStatement st=null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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

    public Car getCarById(int id) {
        Car car = null;
        Connection con = null;
        PreparedStatement st =null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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

    public boolean deleteCar(int id) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
