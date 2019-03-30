package ua.expo.model.dao.mapper;


import ua.expo.model.entity.Exposition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition> {

    private ExhibitionHallMapper hallMapper;

    public ExpositionMapper(ExhibitionHallMapper hallMapper){
        this.hallMapper = hallMapper;
    }

    @Override
    public Exposition extractFromResultSet(ResultSet resultSet) throws SQLException {
        Exposition expo = new Exposition();
        expo.setId(resultSet.getInt("expositions.id"));
        expo.setTheme(resultSet.getString("expositions.theme"));
        expo.setShortDescription(resultSet.getString("expositions.shortDescription"));
        expo.setFullDescription(resultSet.getString("expositions.fullDescription"));
        expo.setTicketsCount(resultSet.getInt("expositions.ticketsCount"));
        expo.setPrice(resultSet.getInt("expositions.price"));
        //todo return without ExhibitionHall
        return expo;
    }

    @Override
    public Exposition makeUnique(Map<Integer, Exposition> cache, Exposition expo) {
        cache.putIfAbsent(expo.getId(), expo);
        return cache.get(expo.getId());
    }

    public ExhibitionHallMapper getHallMapper() {
        return hallMapper;
    }

    public void setHallMapper(ExhibitionHallMapper hallMapper) {
        this.hallMapper = hallMapper;
    }
}
