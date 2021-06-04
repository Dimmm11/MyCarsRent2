package model.DAO.impl;

import model.util.Sql;
import model.DAO.mapper.OrderMapper;
import model.DAO.OrderDAO;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderDao implements OrderDAO {
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean carOrder(Car car, Client client, String driver, int term) {
        boolean result = false;
        Connection con = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.CAR_ORDER);
            st.setInt(1, car.getId());
            if (makeOrder(car, client, driver, term))
                result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException r) {
                r.printStackTrace();
            }
            throw new RuntimeException(e);
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
    public boolean makeOrder(Car car, Client client, String driver, int term) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.MAKE_ORDER);
            st.setInt(1, client.getId());
            st.setInt(2, car.getId());
            st.setString(3, driver);
            st.setInt(4, term);
            st.setBigDecimal(5, car.getPrice().multiply(BigDecimal.valueOf(term)));
            st.setBigDecimal(6, car.getPrice().multiply(BigDecimal.valueOf(term)));
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new RuntimeException(e);
        } finally {
            try {
                st.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public List<Order> getOrdersByClient(Client client) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ORDERS_BY_CLIENT.replaceAll("clientid", String.valueOf(client.getId())));
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByClient(Client client, int index, int offset) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = connection) {
            con.setAutoCommit(false);
            pst = con.prepareStatement(Sql.PAGE_ORDERS_BY_CLIENT);
            pst.setInt(1, client.getId());
            pst.setInt(2, index);
            pst.setInt(3, offset);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection con = connection;
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ORDERS_ALL);
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    @Override
    public List<Order> getAllOrders(int index, int offset) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = connection) {
            pst = con.prepareStatement(Sql.PAGE_ORDERS_ALL);
            pst.setInt(1, index);
            pst.setInt(2, offset);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @Override
    public boolean setReason(int orderId, String reason) {
        boolean result;
        PreparedStatement st = null;
        try (Connection con = connection) {
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.SET_REASON);
            st.setString(1, reason);
            st.setInt(2, orderId);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                st.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean setPenalty(int orderId, BigDecimal penalty) {
        boolean result = false;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        try (Connection con = connection) {
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery(Sql.GET_RENT_COST.replaceAll("orderId", String.valueOf(orderId)));
            rs.next();
            BigDecimal bd = rs.getBigDecimal(1);
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.SET_PENALTY);
            st.setBigDecimal(1, penalty);
            st.setInt(2, orderId);
            st1 = con.prepareStatement(Sql.SET_TOTAL_COST);
            st1.setBigDecimal(1, penalty.add(bd));
            st1.setInt(2, orderId);
            result = (st.executeUpdate() > 0 && st1.executeUpdate() > 0);
            con.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                st.close();
                st1.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean setOrderStatus(int orderId, String orderStatus) {
        boolean result = false;
        PreparedStatement st = null;
        try (Connection con = connection) {
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.SET_ORDER_STATUS);
            st.setString(1, orderStatus);
            st.setInt(2, orderId);
            result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                st.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean carReturn(int orderId, int clientId) {
        boolean result = false;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        PreparedStatement st3 = null;
        try (Connection con = connection) {
            st = con.prepareStatement(Sql.RETURN_CAR);
            st.setInt(1, orderId);
            st2 = con.prepareStatement(Sql.FINISHED_ORDER);
            st2.setInt(1, orderId);
            st2.setInt(2, clientId);
            st3 = con.prepareStatement(Sql.DELETE_ORDER);
            st3.setInt(1, orderId);
            result = (st.executeUpdate() > 0 && st2.executeUpdate() > 0 && st3.executeUpdate() > 0);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                st.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean cancelOrder(int orderId) {
        boolean result = false;
        PreparedStatement st = null;
        PreparedStatement st3 = null;
        Connection con = null;
        try {
            con = connection;
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.RETURN_CAR);
            st.setInt(1, orderId);
            st3 = con.prepareStatement(Sql.DELETE_ORDER);
            st3.setInt(1, orderId);
            result = (st.executeUpdate() > 0 && st3.executeUpdate() > 0);
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ee) {
                ee.printStackTrace();
            }
        } finally {
            try {
                st.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
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
