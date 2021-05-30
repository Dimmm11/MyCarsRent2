package model.DAO.tryService;

import model.DAO.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract CarDAO createCarDao();
    public abstract ClientDAO createClientDao();
    public abstract OrderDAO createOrderDao();

    public static DaoFactory getInstance(){
        if(daoFactory==null){
            daoFactory = new JDBCDaoFactory();
        }
        return daoFactory;
    }

//    public static DaoFactory getInstance(){
//        if( daoFactory == null ){
//            synchronized (DaoFactory.class){
//                if(daoFactory==null){
//                    DaoFactory temp = new JDBCDaoFactory();
//                    daoFactory = temp;
//                }
//            }
//        }
//        return daoFactory;
//    }
}
