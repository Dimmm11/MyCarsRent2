package model.DAO.mapper;

import model.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Mapper to get car from ResultSet
 */
public class CarMapper implements ObjectMapper<Car>{
    @Override
    public Car mapFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setMarque(rs.getString("marque"));
        car.setClazz(rs.getString("car_class"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setStatus(rs.getString("car_status"));
        return car;
    }
}
