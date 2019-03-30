package ua.expo.model.dao.impl;

import ua.expo.model.dao.GenericDAO;
import ua.expo.model.dao.mapper.ExpositionMapper;
import ua.expo.model.entity.ExhibitionHall;
import ua.expo.model.entity.Exposition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCExpositionDao implements GenericDAO<Exposition> {
    private ExpositionMapper expoMapper;
    private Connection connection;

    public JDBCExpositionDao(Connection connection, ExpositionMapper expoMapper){
        this.connection = connection;
        this.expoMapper = expoMapper;
    }

    @Override
    public void insert(Exposition expo) {
        String query = "insert into ExpositionProject.expositions (theme, shortDescription, fullDescription, " +
                "ticketsCount, price, hall_id) values (?, ?, ? , ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getTicketsCount());
            statement.setInt(5, expo.getPrice());
            statement.setInt(6, expo.getHall().getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }


    @Override
    public Exposition getById(int id) {
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.id = ?;";
        ResultSet resultSet;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Exposition expo = expoMapper.extractFromResultSet(resultSet);
                expo.setHall(expoMapper.getHallMapper().extractFromResultSet(resultSet));
                return expo;
            }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Exposition expo) {
        String query = "update ExpositionProject.expositions  set theme = ?, shortDescription = ?, " +
                "fullDescription = ?, ticketsCount = ?, price = ?, hall_id = ? where id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getTicketsCount());
            statement.setInt(5, expo.getPrice());
            statement.setInt(6, expo.getHall().getId());
            statement.setInt(7, expo.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Exposition expo) {
        String query = "delete from ExpositionProject.expositions where id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, expo.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
    //todo now finished
    @Override
    public List<Exposition> getAll() {
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id;";

        Map<Integer, ExhibitionHall> hallsMap = new HashMap<>();
        List<Exposition> list = new ArrayList<>();
        ResultSet resultSet;
        ExhibitionHall tempHall;
        Exposition tempExpo;

        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                tempHall = expoMapper.getHallMapper().extractFromResultSet(resultSet);
                tempHall = expoMapper.getHallMapper().makeUnique(hallsMap, tempHall);
                tempExpo = expoMapper.extractFromResultSet(resultSet);
                tempExpo.setHall(tempHall);
                list.add(tempExpo);
            }
            return list;
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
