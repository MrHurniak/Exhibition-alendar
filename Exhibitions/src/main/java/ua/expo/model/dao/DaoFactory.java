package ua.expo.model.dao;

import ua.expo.model.dao.impl.*;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

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

    public abstract JDBCUserDao createUserDao();
    public abstract JDBCExhibitionHallDao createExhibitionHallDao();
    public abstract JDBCExpositionDao createExpositionDao();
    public abstract JDBCTicketDao createTicketDao();

}
