package ua.training.model.dao.mapper;


import ua.training.model.entity.Exposition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition> {

    @Override
    public Exposition extractFromResultSet(ResultSet resultSet) throws SQLException {
        //todo return without ExhibitionHall
        return new Exposition.Builder()
                .setId(resultSet.getInt("expositions.id"))
                .setTheme(resultSet.getString("expositions.theme"))
                .setShortDescription(resultSet.getString("expositions.shortDescription"))
                .setFullDescription(resultSet.getString("expositions.fullDescription"))
                .setPrice(resultSet.getInt("expositions.price"))
                .setDate(resultSet.getDate("expositions.date"))
                .build();
    }

    @Override
    public Exposition makeUnique(Map<Integer, Exposition> cache, Exposition expo) {
        cache.putIfAbsent(expo.getId(), expo);
        return cache.get(expo.getId());
    }

}
