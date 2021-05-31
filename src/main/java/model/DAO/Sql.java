package model.DAO;

/**
 * sql-quarries
 */
public interface Sql {
    String CLIENTS = "SELECT * FROM clients WHERE role_id=1;";
    String PAGE_CLIENTS = "SELECT * FROM clients WHERE role_id=1 LIMIT ? , ? ;";
    String CLIENT = "SELECT * FROM clients WHERE name = 'login' ;";
//    public static final String CARS = "SELECT * FROM cars;";
    String CARS_BY_CLASS =
        "SELECT * FROM cars WHERE car_class = 'carclass' AND car_status='FREE';";
    String CARS_BY_MARQUE =
            "SELECT * FROM cars WHERE marque= 'carmarque' AND car_status='FREE';";
    String CARS_BY_CLIENT = "SELECT * FROM cars WHERE id IN (SELECT car_id FROM orders WHERE client_id = clientid);";
    String PAGE_CARS_BY_CLIENT = "SELECT * FROM cars WHERE id IN (SELECT car_id FROM orders WHERE client_id = ?) LIMIT ? , ?;";

    String GET_CAR_BY_ID = "SELECT * FROM cars WHERE id= ? ";
    String CAR_ORDER = "UPDATE cars SET car_status='ORDERED' WHERE id= ? ;";
    String MAKE_ORDER = "INSERT INTO orders (client_id,car_id,driver,term, rent_cost,total_cost) VALUES (?,?,?,?,?,?);";
    String ORDERS_BY_CLIENT = "SELECT * FROM orders WHERE client_id= clientid;";
    String PAGE_ORDERS_BY_CLIENT = "SELECT * FROM orders WHERE client_id= ? LIMIT ? , ? ;";

    String SET_REASON = "UPDATE orders SET comment = ?  WHERE id = ? ";
    String SET_PENALTY = "UPDATE orders SET penalty = ?  WHERE id = ? ";
    String SET_TOTAL_COST = "UPDATE orders SET total_cost = ?  WHERE id = ? ";
    String GET_RENT_COST = "SELECT rent_cost FROM orders WHERE id = orderId ;";
    String RETURN_CAR = "UPDATE cars SET car_status = 'FREE' WHERE id = (SELECT car_id FROM orders WHERE id = ? );";
    String FINISHED_ORDER = "INSERT INTO finished_orders (order_id, client_id) VALUES (?,?) ;";
    String DELETE_ORDER = "DELETE FROM orders WHERE id = ? ;";

    String SET_ORDER_STATUS = "UPDATE orders SET confirmed = ?  WHERE id = ?  ;";
//    admin
    String ALLCARS = "SELECT * FROM cars;";
    String PAGE_ALLCARS = "SELECT * FROM cars LIMIT ? , ? ;";

    String ORDERS_ALL = "SELECT * FROM orders;";
    String PAGE_ORDERS_ALL = "SELECT * FROM orders LIMIT ? , ? ;";
    String ORDERED_CARS = "SELECT * FROM cars WHERE car_status='ORDERED';";
    String PAGE_ORDERED_CARS = "SELECT * FROM cars WHERE car_status='ORDERED' LIMIT ? , ? ;";
    String ADMIN_CLIENTS = "SELECT * FROM clients;";
    String ADMIN_STAFF = "SELECT * FROM clients WHERE role_id > 1;";
    String PAGE_ADMIN_STAFF = "SELECT * FROM clients WHERE role_id > 1 LIMIT ? , ? ;";
    String STAFF = "SELECT * FROM clients WHERE role_id IN (3,2);";
    String ADD_CAR = "INSERT INTO cars (marque, car_class, model, price) VALUES (?,?,?,?);";
    String DELETE_CAR = "DELETE FROM cars WHERE id = ?  ;";
    String DELETE_CLIENT = "DELETE FROM clients WHERE name= ? ;";
    String MOVE_CLIENT_TO_REMOVED = "INSERT INTO deleted_clients(id,name,passport) VALUES (?,?,?) ;";
    String MAKE_MANAGER = "UPDATE clients SET role_id=2 WHERE name= ? ;";
    String REMOVE_MANAGER = "UPDATE clients SET role_id=1 WHERE name= ? ;";
    String PRICE_UPDATE = "UPDATE cars SET price = ?  WHERE id= ? ;";
//    register
    String REGISTER = "INSERT INTO clients (name, password, passport) VALUES(?,?,?);";
//    ban
    String BAN = "UPDATE clients SET status='BANNED' WHERE name = ? ;";
    String UNBAN = "UPDATE clients SET status='ACTIVE' WHERE name = ? ;";

// ================
    String DYNAMIC_CAR_CLASS = "SELECT * FROM cars WHERE car_class = ? AND car_status = 'FREE' LIMIT ? , ? ;";
    String DYNAMIC_CAR_MARQUE = "SELECT * FROM cars WHERE marque = ? AND car_status = 'FREE' LIMIT ? , ? ;";



}
