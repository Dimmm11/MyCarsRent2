package model.DAO;

import model.connection.ConnectionPoolHolder;
import model.entity.Car;
import model.entity.Client;
import model.DAO.mapper.CarMapper;

import java.math.BigDecimal;
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
        } catch (SQLException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
        return carsByClass;
    }
    public static List<Car> getCarsByClass(String carClass,String column, String sortingOrder) {
        List<Car> carsByClass = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = SqlQuarry.CARS_BY_CLASS.replace("carclass", carClass).replace(";", " ORDER BY "+column+" "+sortingOrder+" ;");
            System.out.println("sql string: "+sql);
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



    public static List<Car> getCarsByClass(String carClass, int index, int offset) {
        List<Car> carsByClass = new ArrayList<>();
        PreparedStatement stt = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            stt = con.prepareStatement(SqlQuarry.DYNAMIC_CAR_CLASS);
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
    public static List<Car> getCarsByMarque(String marque,String column, String sortingOrder) {
        List<Car> carsByMarque = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String sql = SqlQuarry.CARS_BY_MARQUE.replaceAll("carmarque", marque).replace(";", " ORDER BY "+column+" "+sortingOrder+" ;");
            System.out.println("sql: "+sql);
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

    public static List<Car> getCarsByMarque(String marque, int index, int offset) {
        List<Car> carsByMarque = new ArrayList<>();
        PreparedStatement stt = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            stt = con.prepareStatement(SqlQuarry.DYNAMIC_CAR_MARQUE);
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

    public static synchronized List<Car> getCarsByClient(Client client, int index, int offset) {
        List<Car> carsByClient = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            con.setAutoCommit(false);
            pst = con.prepareStatement(SqlQuarry.PAGE_CARS_BY_CLIENT);
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
// ==============================================================================
    public static List<Car> getAllCars(String column, String sortingOrder) {
        List<Car> allCars = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String ss = SqlQuarry.ALLCARS.replaceAll(";"," ORDER BY "+column+" "+sortingOrder+" ;");
            System.out.println(ss);
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

    public static List<Car> getAllCars(List<Car> allCars,int startIndex, int lastIndex) {
        List<Car> sortedCars = new ArrayList<>();
        System.out.println("startIndex: "+startIndex);
        if(allCars.size()-1 < lastIndex){
            lastIndex =startIndex+ ((allCars.size())-startIndex);
            System.out.println("lastIndex: "+lastIndex);
        }
        sortedCars = allCars.subList(startIndex, lastIndex);

        return sortedCars;
    }


// ===================================================================================
    public static List<Car> getAllCars(int index, int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement st = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            st = con.prepareStatement(SqlQuarry.PAGE_ALLCARS);
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
    public static List<Car> getOrderedCars(int index,int offset) {
        List<Car> allCars = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            pst = con.prepareStatement(SqlQuarry.PAGE_ORDERED_CARS);
            pst.setInt(1,index);
            pst.setInt(2,offset);
            rs = pst.executeQuery();
            while (rs.next()) {
                Car car = new CarMapper().mapFromResultSet(rs);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                pst.close();
                rs.close();
            }catch (SQLException e){
                e.getMessage();
            }
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

    public static synchronized boolean updatePrice(BigDecimal price, Car car) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(SqlQuarry.PRICE_UPDATE);

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
