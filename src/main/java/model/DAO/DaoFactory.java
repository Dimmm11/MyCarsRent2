package model.DAO;

import model.DAO.impl.JDBCDaoFactory;

/**
 * Factory to make Dao instances
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract CarDAO createCarDao();
    public abstract ClientDAO createClientDao();
    public abstract OrderDAO createOrderDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
