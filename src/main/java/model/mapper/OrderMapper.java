package model.mapper;

import model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements ObjectMapper<Order>{
    @Override
    public Order mapFromResultSet(ResultSet rs) throws SQLException {

       Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setClient_id(rs.getInt("client_id"));
        order.setCar_id(rs.getInt("car_id"));
        order.setDriver(rs.getString("driver"));
        order.setTerm(rs.getInt("term"));
        order.setRent_cost(rs.getBigDecimal("rent_cost"));
        order.setPenalty(rs.getBigDecimal("penalty"));
        order.setConfirmed(rs.getString("confirmed"));
        order.setTotal_cost(rs.getBigDecimal("total_cost"));
        order.setComment(rs.getString("comment"));
        return order;
    }
}
