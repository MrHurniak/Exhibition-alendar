package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;
import ua.training.model.exceptions.NotUniqEMailException;
import ua.training.model.exceptions.NotUniqLoginException;

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
    public void insert(User user) {
        String query1 = "SELECT * FROM ExpositionProject.users where users.login = ?;";
        String query2 = "SELECT * FROM ExpositionProject.users where users.email = ?;";
        String query3 = "insert into ExpositionProject.users (name, surname, email, login, password, role) " +
                "values (?, ?, ?, ?, ?, ?);";
        ResultSet resultSet;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(query1)) {
                statement.setString(1, user.getLogin());
                resultSet = statement.executeQuery();
                LOGGER.info("Search users with login=" + user.getLogin());
                if (resultSet.next()) {
                    LOGGER.info("Matching logins found. Login=" + user.getLogin());
                    throw new NotUniqLoginException(user.getLogin(), user.getName(), user.getSurname(), user.getEmail());
                }
            } catch (SQLException | NotUniqLoginException e){
                connection.rollback();
                connection.setAutoCommit(true);
                throw e;
            }
            try(PreparedStatement statement = connection.prepareStatement(query2)){
                statement.setString(1, user.getEmail());
                resultSet = statement.executeQuery();
                LOGGER.info("Searching users with email=" + user.getEmail());
                if (resultSet.next()) {
                    LOGGER.info("Matching emails found. Email=" + user.getEmail());
                    throw new NotUniqEMailException(user.getLogin(), user.getName(), user.getSurname(), user.getEmail());
                }
            } catch (SQLException | NotUniqEMailException e){
                connection.rollback();
                connection.setAutoCommit(true);
                throw e;
            }
            try(PreparedStatement statement = connection.prepareStatement(query3)){
                commonExtract(user, statement);
                statement.execute();
                LOGGER.info("Added new user");
            } catch (SQLException e){
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.error("Can`t add new user with login=" + user.getLogin(), e);
                throw e;
            }
            connection.commit();
            connection.setAutoCommit(true);
            LOGGER.info("Successfully added new user to user table. User login=" + user.getLogin());
        } catch (SQLException e) {
            LOGGER.error("Can`t add user with login=" + user.getLogin() + " to user table", e);
        }
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
