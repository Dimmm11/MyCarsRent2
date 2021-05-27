package model.service;

import model.DAO.SqlQuarry;
import model.connection.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckClient {
    public static boolean isValidClient(String login, String password) {
        boolean result = false;
        String name = SqlQuarry.CLIENT.replace("login", login);
        String log = "";
        String pass = "";
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(name);
            if (rs.next()) {
                log = rs.getString(2);
                pass = rs.getString(3);
            }
            result = (log.equals(login) && pass.equals(password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
