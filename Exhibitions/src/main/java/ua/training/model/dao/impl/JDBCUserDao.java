package ua.training.model.dao.impl;

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

    private UserMapper userMapper;
    private DataSource dataSource;

    JDBCUserDao(DataSource dataSource){
        this.userMapper = UserMapper.getInstance();
        this.dataSource = dataSource;
    }

    @Override
    public void insert(User user) {
        String query = "insert into ExpositionProject.users (name, surname, email, login, password, role) " +
                "values (?, ?, ?, ?, ?, ?);";
        try(Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            commonExtract(user, statement);
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
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

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM ExpositionProject.users where id=?;";
        ResultSet resultSet;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return null;
    }
    //make case sensitive query to DB
    public User getByLogin(String login) {
        String query = "SELECT * FROM ExpositionProject.users where BINARY users.login = ?;";
        ResultSet resultSet;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(User user) {
        String query = "update ExpositionProject.users set name=?, surname=?, email=?, " +
                "login=?,password=?,role=? where id=?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            commonExtract(user, statement);
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User user) {
        String query = "delete from ExpositionProject.users where id = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll(){
        String query = "select * from ExpositionProject.users";
        ResultSet resultSet;
        List<User> list = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(userMapper.extractFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
