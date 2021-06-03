package model.DAO.impl;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DBConnector {
  public static DataSource getDataSource(){
      MysqlDataSource mds = new MysqlDataSource();
      mds.setURL("jdbc:mysql://localhost:3306/cars_rent_test?serverTimezone=EET");
      mds.setDatabaseName("cars_rent_test");
      mds.setUser("root");
      mds.setPassword("root");
      return mds;
  }
}
