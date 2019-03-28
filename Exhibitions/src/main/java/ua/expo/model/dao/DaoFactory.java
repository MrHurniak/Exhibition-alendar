package ua.expo.model.dao;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
//                    DaoFactory temp = new JDBCDaoFactory();
//                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
