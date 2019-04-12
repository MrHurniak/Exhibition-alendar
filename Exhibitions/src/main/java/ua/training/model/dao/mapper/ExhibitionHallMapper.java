package ua.training.model.dao.mapper;

import ua.training.model.entity.ExhibitionHall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionHallMapper implements ObjectMapper<ExhibitionHall> {
    @Override
    public ExhibitionHall extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new ExhibitionHall.Builder()
                .setId(resultSet.getInt("exhibitionHalls.id"))
                .setName(resultSet.getString("exhibitionHalls.name"))
                .setInformation(resultSet.getString("exhibitionHalls.information"))
                .build();
    }

    @Override
    public ExhibitionHall makeUnique(Map<Integer, ExhibitionHall> cache, ExhibitionHall hall) {
        cache.putIfAbsent(hall.getId(), hall);
        return cache.get(hall.getId());
    }
}
