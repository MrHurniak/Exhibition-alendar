package ua.training.model.dao.mapper;

import ua.training.model.entity.ExhibitionHall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionHallMapper implements ObjectMapper<ExhibitionHall> {
    @Override
    public ExhibitionHall extractFromResultSet(ResultSet resultSet) throws SQLException {
        ExhibitionHall hall = new ExhibitionHall();
        hall.setId(resultSet.getInt("exhibitionHalls.id"));
        hall.setName(resultSet.getString("exhibitionHalls.name"));
        hall.setInformation(resultSet.getString("exhibitionHalls.information"));
        return hall;
    }

    @Override
    public ExhibitionHall makeUnique(Map<Integer, ExhibitionHall> cache, ExhibitionHall hall) {
        cache.putIfAbsent(hall.getId(), hall);
        return cache.get(hall.getId());
    }
}
