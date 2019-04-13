package ua.training.model.dao.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource;

    private UserMapper userMapper;
    private ExpositionMapper expositionMapper;
    private ExhibitionHallMapper hallMapper;

    public JDBCDaoFactory(){
        dataSource = ConnectionPoolHolder.getDataSource();
        userMapper = new UserMapper();
        hallMapper = new ExhibitionHallMapper();
        expositionMapper = new ExpositionMapper();

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
        return new JDBCUserDao(getConnection(), userMapper);
    }

    @Override
    public JDBCExhibitionHallDao createExhibitionHallDao() {
        return new JDBCExhibitionHallDao(getConnection(), hallMapper);
    }

    @Override
    public JDBCExpositionDao createExpositionDao() {
        return new JDBCExpositionDao(getConnection(), expositionMapper, hallMapper);
    }

    @Override
    public JDBCTicketDao createTicketDao() {
        return new JDBCTicketDao(getConnection(), userMapper, expositionMapper, hallMapper);
    }

}
