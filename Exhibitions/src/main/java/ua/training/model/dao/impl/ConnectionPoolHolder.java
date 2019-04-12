package ua.training.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static final String DB_PROPERTIES = "/DBinfo.properties";
    private static final String DRIVER = "database.driver";
    private static final String URL = "database.url";
    private static final String USER = "database.user";
    private static final String PASSWORD = "database.password";
    private static volatile DataSource dataSource;
    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {

                    try (InputStream stream = ConnectionPoolHolder.class.getResourceAsStream(DB_PROPERTIES)){
                        Properties properties = new Properties();
                        properties.load(stream);

//                        Class.forName("com.mysql.jdbc.Driver");
                        Class.forName(properties.getProperty(DRIVER));
                        BasicDataSource ds = new BasicDataSource();
//                        ds.setUrl("jdbc:mysql://localhost:3306/ExpositionProject");
                        ds.setUrl(properties.getProperty(URL));
//                        ds.setUsername("root");
//                        ds.setPassword("password");
                        ds.setUsername(properties.getProperty(USER));
                        ds.setPassword(properties.getProperty(PASSWORD));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }
}
