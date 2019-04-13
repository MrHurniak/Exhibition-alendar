package ua.training.model.dao.impl;

import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;

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
    private ExhibitionHallMapper hallMapper;

    public JDBCExpositionDao(Connection connection, ExpositionMapper expoMapper, ExhibitionHallMapper hallMapper){
        this.connection = connection;
        this.expoMapper = expoMapper;
        this.hallMapper = hallMapper;
    }

    @Override
    public void insert(Exposition expo) {
        String query = "insert into ExpositionProject.expositions (theme, shortDescription, fullDescription, " +
                "price, date, hall_id) values (?, ?, ? , ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getPrice());
            statement.setDate(5, expo.getDate());
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
                expo.setHall(hallMapper.extractFromResultSet(resultSet));
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
                "fullDescription = ?, price = ?,date = ?, hall_id = ? where id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getPrice());
            statement.setInt(5, expo.getHall().getId());
            statement.setDate(6, expo.getDate());
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
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            return getList(statement);
        }catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getInRange(int offset, int length){
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id limit ?,?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, offset);
            statement.setInt(2, length);
            return getList(statement);
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getInRangeHall(int offset, int length, int hallId) {
        String query = "SELECT  * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.hall_id = ? " +
                "limit ?,?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, hallId);
            statement.setInt(2, offset);
            statement.setInt(3, length);
            return getList(statement);
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }


    public int getNumberRows(int id) {
        String query = "SELECT count(expositions.id) as count FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.hall_id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("count");
            }

        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getNumberRows() {
        String query = "SELECT count(expositions.id) as count FROM ExpositionProject.expositions;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt("count");
            }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return 0;
    }

    private List<Exposition> getList(PreparedStatement statement) throws SQLException{
        Map<Integer, ExhibitionHall> hallsMap = new HashMap<>();
        List<Exposition> list = new ArrayList<>();
        ResultSet resultSet;
        ExhibitionHall tempHall;
        Exposition tempExpo;
        resultSet = statement.executeQuery();
        while (resultSet.next()){
            tempHall = hallMapper.extractFromResultSet(resultSet);
            tempHall = hallMapper.makeUnique(hallsMap, tempHall);
            tempExpo = expoMapper.extractFromResultSet(resultSet);
            tempExpo.setHall(tempHall);
            list.add(tempExpo);
        }
        return list;
    }


}
