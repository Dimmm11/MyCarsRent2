package model.service;

import model.DAO.Sql;
import model.connection.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckClient {
    /**
     * Accepts input
     * @param login
     * @param password
     * to check by DB
     * @return true, if client is present in DB
     */
    public static boolean isValidClient(String login, String password) {
        boolean result = false;
        String name = Sql.CLIENT.replace("login", login);
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
