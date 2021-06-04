package model.DAO.impl;

import model.util.Sql;
import model.DAO.mapper.ClientMapper;
import model.DAO.ClientDAO;
import model.entity.Client;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCClientDao implements ClientDAO {
    private Connection connection;

    public JDBCClientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(Sql.CLIENTS)) {
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Client> getClients(int index, int offset) {
        List<Client> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = connection) {
            pst = con.prepareStatement(Sql.PAGE_CLIENTS);
            pst.setInt(1, index);
            pst.setInt(2, offset);
            rs = pst.executeQuery();
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    @Override
    public Client getClient(String login) {
        String name = Sql.CLIENT.replaceAll("login", login);
        Client client = null;
        try (Connection con = connection;
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(name)) {
            rs.next();
            client = new ClientMapper().mapFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getStaff() {
        List<Client> staff = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(Sql.ADMIN_STAFF)) {
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                staff.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<Client> getStaff(int index, int offset) {
        List<Client> staff = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = connection) {
            pst = con.prepareStatement(Sql.PAGE_ADMIN_STAFF);
            pst.setInt(1, index);
            pst.setInt(2, offset);
            rs = pst.executeQuery();
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                staff.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return staff;
    }

    /**
     * Transaction
     *
     * @param login - client name
     * @return true if client deleted
     */
    @Override
    public boolean deleteClient(String login) {
        Client client = getClient(login);
        boolean result = false;
        Connection con = null;
        PreparedStatement stt = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            stt = con.prepareStatement(Sql.MOVE_CLIENT_TO_REMOVED);
            stt.setInt(1, client.getId());
            stt.setString(2, client.getLogin());
            stt.setString(3, client.getPassport());
            st = con.prepareStatement(Sql.DELETE_CLIENT);
            st.setString(1, login);
            result = (stt.executeUpdate() > 0 && st.executeUpdate() > 0);
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
                stt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    @Override
    public boolean makeManager(String login) {
        boolean result = false;
        try (Connection con = connection) {
            Statement st = con.createStatement();
            result = st.executeUpdate(Sql.MAKE_MANAGER
                    .replace(" ? ", "'" + login + "'")) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeManager(String login) {
        boolean result = false;
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            result = st.executeUpdate(Sql.REMOVE_MANAGER.replace(" ? ", "'" + login + "'")) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Registration in DB using Bcrypt to hash password
     *
     * @param client incoming Client from Registration form
     * @return result or Registration try
     */
    @Override
    public boolean register(Client client) {
        boolean result = false;
        Connection con = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.REGISTER);
            st.setString(1, client.getLogin());
            String pass = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt(5));
            st.setString(2, pass);
            st.setString(3, client.getPassport());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean ban(String login) {
        boolean result = false;
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            result = st.executeUpdate(Sql.BAN
                    .replace(" ? ", "'" + login + "'")) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean unBan(String login) {
        boolean result = false;
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            result = st.executeUpdate(Sql.UNBAN
                    .replace(" ? ", "'" + login + "'")) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
