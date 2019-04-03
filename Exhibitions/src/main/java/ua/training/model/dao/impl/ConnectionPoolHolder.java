package ua.training.model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {

                    try {
//                        Properties properties = new Properties();

//                        properties.load(new FileInputStream(
//                                "D:\\Projects\\Idea\\Training\\ConferenceFinal\\src\\main\\resources\\db.properties"));


//                        Class.forName(properties.getProperty("db.connection.driver"));
                        Class.forName("com.mysql.jdbc.Driver");
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl("jdbc:mysql://localhost:3306/ExpositionProject");
                        ds.setUsername("root");
                        ds.setPassword("password");
//                        ds.setUrl(properties.getProperty("db.connection.url"));
//                        ds.setUsername(properties.getProperty("db.connection.username"));
//                        ds.setPassword(properties.getProperty("db.connection.password"));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(10);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;

                    } catch (/*IOException | */ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return dataSource;
    }

}
