package ua.training.model.dao.mapper;


import ua.training.model.entity.Exposition;
import ua.training.model.entity.enums.ExpositionStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExpositionMapper implements ObjectMapper<Exposition> {
    private static volatile ExpositionMapper instance;

    private ExpositionMapper(){}

    public static ExpositionMapper getInstance(){
        if(instance == null){
            synchronized (ExpositionMapper.class){
                if(instance == null){
                    instance = new ExpositionMapper();
                }
            }
        }
        return instance;
    }

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
                .setDateTo(resultSet.getDate("expositions.date_to"))
                .setExpositionStatus(ExpositionStatus.valueOf(resultSet.getString("expositions.state")))
                .build();
    }

    @Override
    public Exposition makeUnique(Map<Integer, Exposition> cache, Exposition expo) {
        cache.putIfAbsent(expo.getId(), expo);
        return cache.get(expo.getId());
    }

}
