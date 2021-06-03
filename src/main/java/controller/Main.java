package controller;

import com.mysql.cj.jdbc.MysqlDataSource;
import model.DAO.mapper.CarMapper;
import model.entity.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MysqlDataSource mds = new MysqlDataSource();
        mds.setURL("jdbc:mysql://localhost:3306/cars_rent?serverTimezone=EET");
        mds.setDatabaseName("cars_rent");
        mds.setUser("root");
        mds.setPassword("root");
        List<Car> list = new ArrayList<>();
        try (Connection con = mds.getConnection();){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cars;");
            while (rs.next()){
                Car car = new CarMapper().mapFromResultSet(rs);
                list.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
    }
}
