package model.DAO;

import model.connection.ConnectionPoolHolder;
import model.entity.Car;
import model.entity.Client;
import model.entity.Order;
import model.DAO.mapper.OrderMapper;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static synchronized boolean carOrder(Car car, Client client, String driver, int term) {
        boolean result = false;
        Connection con = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement(Sql.CAR_ORDER);
            st.setInt(1, car.getId());
            if (OrderDAO.makeOrder(car, client, driver, term))
                result = st.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to MAKE ORDER in OrderDAO.carOrder()");
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

    public static synchronized boolean makeOrder(Car car, Client client, String driver, int term) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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
            System.out.println("FAILED to change orders in OrderDAO.makeOrder()");
            System.out.println(e.getMessage());
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                st.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static synchronized List<Order> getOrdersByClient(Client client) {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ORDERS_BY_CLIENT.replaceAll("clientid", String.valueOf(client.getId())));
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("FAILED to get Orders FROM orders (OrderDAO.getOrders)");
            System.out.println(e.getMessage());
        }
        return orders;
    }

    public static synchronized List<Order> getOrdersByClient(Client client, int index, int offset) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pst = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
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
            System.out.println("FAILED to get Orders FROM orders (OrderDAO.getOrders)");
            System.out.println(e.getMessage());
        }
        return orders;
    }


    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(Sql.ORDERS_ALL);
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("FAILED to get Orders FROM orders (OrderDAO.getOrders)");
            System.out.println(e.getMessage());
        }
        return orders;
    }
    public static List<Order> getAllOrders(int index, int offset) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try (Connection con = ConnectionPoolHolder.getDataSource().getConnection()) {
            pst = con.prepareStatement(Sql.PAGE_ORDERS_ALL);
            pst.setInt(1,index);
            pst.setInt(2,offset);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new OrderMapper().mapFromResultSet(rs);
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("FAILED OrderDAO.getAllOrders(PAGE)");
            System.out.println(e.getMessage());
        }finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public static synchronized void setReason(int orderId, String reason) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.SET_REASON);
            st.setString(1, reason);
            st.setInt(2, orderId);
            st.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to set REASON OrderDAO");
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static synchronized void setPenalty(int orderId, BigDecimal penalty) {
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st1 = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            Statement stt = con.createStatement();
            ResultSet rs = stt.executeQuery(Sql.GET_RENT_COST.replaceAll("orderId", String.valueOf(orderId)));
            rs.next();
            BigDecimal bd = rs.getBigDecimal(1);

            con.setAutoCommit(false);

            st = con.prepareStatement(Sql.SET_PENALTY);
            st.setBigDecimal(1, penalty);
            st.setInt(2, orderId);
            st.executeUpdate();

            st1 = con.prepareStatement(Sql.SET_TOTAL_COST);
            st1.setBigDecimal(1, penalty.add(bd));
            st1.setInt(2, orderId);
            st1.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to set PENALTY OrderDAO");
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                st1.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static synchronized void setOrderStatus(int orderId, String reason) {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(Sql.SET_ORDER_STATUS);
            st.setString(1, reason);
            st.setInt(2, orderId);
            st.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to set REASON OrderDAO");
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static synchronized boolean carReturn(int orderId, int clientId) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st2 = null;
        PreparedStatement st3 = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
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
            System.out.println("FAILED to DELETE ORDER in OrderDAO.carReturn");
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    public static synchronized boolean cancelOrder(int orderId) {
        boolean result = false;
        Connection con = null;
        PreparedStatement st = null;
        PreparedStatement st3 = null;
        try {
            con = ConnectionPoolHolder.getDataSource().getConnection();
            st = con.prepareStatement(Sql.RETURN_CAR);
            st.setInt(1, orderId);

            st3 = con.prepareStatement(Sql.DELETE_ORDER);
            st3.setInt(1, orderId);

            result = (st.executeUpdate() > 0 && st3.executeUpdate() > 0);
            con.commit();
        } catch (SQLException e) {
            System.out.println("FAILED to DELETE ORDER in OrderDAO.cancelOrder");
            System.out.println(e.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (NullPointerException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
