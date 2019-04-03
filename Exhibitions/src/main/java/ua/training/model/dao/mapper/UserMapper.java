package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("users.id"));
        user.setName(resultSet.getString("users.name"));
        user.setSurname(resultSet.getString("users.surname"));
        user.setEmail(resultSet.getString("users.email"));
        user.setLogin(resultSet.getString("users.login"));
        user.setPassword(resultSet.getString("users.password"));
        user.setRole(Role.valueOf(resultSet.getString("users.role")));
//        user.setRole(resultSet.getInt("users.role"));
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
