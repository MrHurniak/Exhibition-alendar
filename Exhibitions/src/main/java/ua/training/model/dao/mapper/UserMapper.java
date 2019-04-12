package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getInt("users.id"))
                .setName(resultSet.getString("users.name"))
                .setSurname(resultSet.getString("users.surname"))
                .setEmail(resultSet.getString("users.email"))
                .setLogin(resultSet.getString("users.login"))
                .setPassword(resultSet.getString("users.password"))
                .setRole(Role.valueOf(resultSet.getString("users.role")))
                .build();
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
