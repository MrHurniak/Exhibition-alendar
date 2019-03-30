package ua.expo.model.dao.impl;

import ua.expo.model.dao.DaoFactory;
import ua.expo.model.dao.mapper.ExhibitionHallMapper;
import ua.expo.model.dao.mapper.ExpositionMapper;
import ua.expo.model.dao.mapper.ObjectMapper;
import ua.expo.model.dao.mapper.UserMapper;
import ua.expo.model.entity.ExhibitionHall;
import ua.expo.model.entity.Exposition;
import ua.expo.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private UserMapper userMapper;
    private ExpositionMapper expositionMapper;
    private ExhibitionHallMapper hallMapper;

    public JDBCDaoFactory(){
        userMapper = new UserMapper();
        hallMapper = new ExhibitionHallMapper();
        expositionMapper = new ExpositionMapper(hallMapper);
    }


    private Connection getConnection(){
        //todo maybe singletone must be
        try {
            //todo data from properties file
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ExpositionProject",
                    "root" ,
                    "password" );
        } catch (SQLException e) {
            //todo catch or make beaut
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
        return new JDBCExpositionDao(getConnection(), expositionMapper);
    }

    @Override
    public JDBCTicketDao createTicketDao() {
        return new JDBCTicketDao(getConnection(), userMapper, expositionMapper);
    }
}
