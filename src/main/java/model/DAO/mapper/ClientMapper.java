package model.DAO.mapper;

import model.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements ObjectMapper<Client>{
    @Override
    public Client mapFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setLogin(rs.getString("name"));
        client.setPassport(rs.getString("passport"));
        client.setPassword(rs.getString("password"));
        client.setRole_id(rs.getInt("role_id"));
        client.setStatus(rs.getString("status"));
        return client;
    }
}
