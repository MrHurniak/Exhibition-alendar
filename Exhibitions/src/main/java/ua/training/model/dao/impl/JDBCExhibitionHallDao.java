package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.entity.ExhibitionHall;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCExhibitionHallDao implements GenericDAO<ExhibitionHall> {

    private final static Logger LOGGER = Logger.getLogger(JDBCExpositionDao.class);

    private DataSource dataSource;
    private ExhibitionHallMapper hallMapper;

    JDBCExhibitionHallDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.hallMapper = ExhibitionHallMapper.getInstance();
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public void insert(ExhibitionHall hall) {
        String query =
                "insert into ExpositionProject.exhibitionHalls (name, information, state) values (?,?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getInformation());
            statement.setString(3, hall.getStatus().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException while insert ExhibitionHall instance with name=" + hall.getName(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Inserted new hall with name=" + hall.getName());
    }

    @Override
    public ExhibitionHall getById(int id) {
        String query = "SELECT * FROM ExpositionProject.exhibitionHalls where id = ?;";
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            LOGGER.info("Successful execution of select query by hall id=" + id);
            if (resultSet.next()) {
                return hallMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException while get ExhibitionHall instance by id=" + id, e);
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void update(ExhibitionHall hall) {
        String query = "update ExpositionProject.exhibitionHalls set name=?, information=?, state=? where id=?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getInformation());
            statement.setString(3, hall.getStatus().name());
            statement.setInt(4, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException while trying update hall with id=" + hall.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Update hall id=" + hall.getId() + ", name=" + hall.getName());
    }

    public void saveDelete(ExhibitionHall hall) {
        String query1 = "update ExpositionProject.exhibitionHalls set state='DELETED' where id=?;";
        String query2 = "update ExpositionProject.expositions  set state='DELETED' where hall_id = ?;";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(query1);
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setInt(1, hall.getId());
            statement2.executeUpdate();
            statement1.setInt(1, hall.getId());
            statement1.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("SQLException trying update exhibitionHalls and expositions tables " +
                    "by seting state 'DELETED' by hall with id=" + hall.getId(), e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException q) {
                LOGGER.error("SQLException trying to do rollback", e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQLException trying to close connection", e);
            }
        }
        LOGGER.info("Performed save 'delete' in halls and expos tables by hall with id=" + hall.getId());
    }


    @Override
    public void delete(ExhibitionHall hall) {
        String query = "delete from ExpositionProject.exhibitionHalls where id = ?; ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException trying to delete hall with id=" + hall.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.warn("Performed delete of hall with id=" + hall.getId());
    }

    @Override
    public List<ExhibitionHall> getAll() {
        String query = "select * from ExpositionProject.exhibitionHalls;";
        LOGGER.info("Trying to get all halls");
        return getList(query);
    }

    public List<ExhibitionHall> getAllOK() {
        String query = "select * from ExpositionProject.exhibitionHalls where exhibitionHalls.state='OK';";
        LOGGER.info("Trying to getAll hall with 'OK' state");
        return getList(query);
    }

    private List<ExhibitionHall> getList(String query) {
        List<ExhibitionHall> list = new ArrayList<>();
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(hallMapper.extractFromResultSet(resultSet));
            }
            LOGGER.info("Performed select of hall by specified query");
            return list;
        } catch (SQLException e) {
            LOGGER.error("SQLException while select halls by query", e);
            throw new RuntimeException(e);
        }

    }
}
