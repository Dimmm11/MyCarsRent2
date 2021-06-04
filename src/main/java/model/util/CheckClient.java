package model.util;

import model.DAO.impl.ConnectionPoolHolder;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckClient {
    /**
     * Accepts input
     * @param login
     * @param password
     * to check by DB. Passwords in DB are hashed
     * @return true, if client is present in DB
     */
    public static boolean isValidClient(String login, String password) {
        boolean result = false;
        String name = Sql.CLIENT.replace("login", login);
        String log = "";
        String hashPassword = "";
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(name);
            if (rs.next()) {
                log = rs.getString(2);
                hashPassword = rs.getString(3);
            }
            result = (log.equals(login) && BCrypt.checkpw(password, hashPassword));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
