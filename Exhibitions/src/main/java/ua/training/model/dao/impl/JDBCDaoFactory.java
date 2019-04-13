package ua.training.model.dao.impl;

import ua.training.model.dao.DaoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource;


    public JDBCDaoFactory(){
        dataSource = ConnectionPoolHolder.getDataSource();

    }

    private Connection getConnection(){
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public JDBCExhibitionHallDao createExhibitionHallDao() {
        return new JDBCExhibitionHallDao(getConnection());
    }

    @Override
    public JDBCExpositionDao createExpositionDao() {
        return new JDBCExpositionDao(getConnection());
    }

    @Override
    public JDBCTicketDao createTicketDao() {
        return new JDBCTicketDao(getConnection());
    }

}
