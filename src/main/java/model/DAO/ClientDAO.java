package model.DAO;

import model.connection.ConnectionPoolHolder;
import model.entity.Client;
import model.DAO.mapper.ClientMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public static List<Client> getClients() {
        List<Client> list = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
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
    public static List<Client> getClients(int index, int offset) {
        List<Client> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs=null;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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
        }finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public static List<Client> getClients(String column, String sortingOrder) {
        List<Client> clients = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            String ss = Sql.CLIENTS.replaceAll(";"," ORDER BY "+column+" "+sortingOrder+" ;");
//            System.out.println(ss);
            ResultSet rs = st.executeQuery(ss);
            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public static Client getClient(String login) {
        String name = Sql.CLIENT.replaceAll("login", login);
        Client client = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(name)) {
            rs.next();
            client = new ClientMapper().mapFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static List<Client> getStaff() {
        List<Client> staff = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
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
    public static List<Client> getStaff(int index, int offset) {
        List<Client> staff = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            pst = con.prepareStatement(Sql.PAGE_ADMIN_STAFF);
            pst.setInt(1,index);
            pst.setInt(2,offset);

            rs = pst.executeQuery();

            ClientMapper cm = new ClientMapper();
            while (rs.next()) {
                Client client = cm.mapFromResultSet(rs);
                staff.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return staff;
    }

    public static synchronized boolean deleteClient(String login) {
        Client client = getClient(login);
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);

            PreparedStatement stt = con.prepareStatement(Sql.MOVE_CLIENT_TO_REMOVED);
            stt.setInt(1, client.getId());
            stt.setString(2, client.getLogin());
            stt.setString(3, client.getPassport());

            PreparedStatement st = con.prepareStatement(Sql.DELETE_CLIENT);
            st.setString(1, login);
            result = (stt.executeUpdate() > 0 && st.executeUpdate() > 0);

            con.commit();

        } catch (SQLException e) {
            System.out.println("FAILED to delete client");
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

    public static synchronized boolean makeManager(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.MAKE_MANAGER);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to make manager");
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

    public static synchronized boolean removeManager(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.REMOVE_MANAGER);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to remove manager");
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

    public static synchronized boolean register(Client client) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.REGISTER);
            st.setString(1, client.getLogin());
            st.setString(2, client.getPassword());
            st.setString(3, client.getPassport());
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException("Such user exists");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static synchronized boolean ban(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.BAN);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to BAN");
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

    public static synchronized boolean unBan(String login) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.UNBAN);
            st.setString(1, login);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to BAN");
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
