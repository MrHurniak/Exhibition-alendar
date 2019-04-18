package ua.training.model.dao.impl;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.UserMapper;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private UserMapper userMapper;
    private ExpositionMapper expositionMapper;
    private ExhibitionHallMapper hallMapper;
    private Properties properties;

    public JDBCDaoFactory(){
        userMapper = new UserMapper();
        hallMapper = new ExhibitionHallMapper();
        expositionMapper = new ExpositionMapper(hallMapper);
        properties = new Properties();

    }

    private Connection getConnection(){
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    private Connection getConnection(){
//        //todo maybe singletone must be
//        try {
//            //todo data from properties file
//            return DriverManager.getConnection(/*getDBinfo("url")*/ "jdbc:mysql://localhost:3306/ExpositionProject"
//                    , /*getDBinfo("user")*/ "root"
//                    , /*getDBinfo("password")*/"password");
//        } catch (SQLException e) {
//            //todo catch or make beaut
//            throw new RuntimeException(e);
//        }
//    }

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

    private String getDBinfo(String key){
        String propertiesFile = "src/main/resources/DBinfo";
        try(InputStream input = new FileInputStream(propertiesFile)){
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
