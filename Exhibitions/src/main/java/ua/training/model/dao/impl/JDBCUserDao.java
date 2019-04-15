package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements GenericDAO<User> {

    private final static Logger LOGGER = Logger.getLogger(JDBCUserDao.class);

    private UserMapper userMapper;
    private DataSource dataSource;

    JDBCUserDao(DataSource dataSource) {
        this.userMapper = UserMapper.getInstance();
        this.dataSource = dataSource;
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public void insert(User user) {
        String query = "insert into ExpositionProject.users (name, surname, email, login, password, role) " +
                "values (?, ?, ?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            commonExtract(user, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException while insert user with login=" + user.getLogin(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Inserted new user with login=" + user.getLogin());
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM ExpositionProject.users where id=?;";
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            LOGGER.info("Successful execution of select user with id=" + id);
            if (resultSet.next()) {
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException while get user with id=" + id, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getByLogin(String login) {
        String query = "SELECT * FROM ExpositionProject.users where BINARY users.login = ?;";
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            LOGGER.info("Search users with login=" + login);
            if (resultSet.next()) {
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException while searching user with login=" + login);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(User user) {
        String query = "update ExpositionProject.users set name=?, surname=?, email=?, " +
                "login=?,password=?,role=? where id=?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            commonExtract(user, statement);
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException while trying update user with id=" + user.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Update user with id=" + user.getId() + ", login=" + user.getLogin());
    }

    @Override
    public void delete(User user) {
        String query = "delete from ExpositionProject.users where id = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException trying to delete user with id=" + user.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.warn("Performed delete of user with id=" + user.getId());
    }

    @Override
    public List<User> getAll() {
        String query = "select * from ExpositionProject.users";
        ResultSet resultSet;
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(userMapper.extractFromResultSet(resultSet));
            }
            LOGGER.info("Request to get all users, return list with size=" + list.size());
            return list;
        } catch (SQLException e) {
            LOGGER.error("SQLException while get all users", e);
            throw new RuntimeException(e);
        }
    }

    private void commonExtract(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getLogin());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getRole().name());
    }
}
