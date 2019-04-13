package ua.training.model.dao.impl;

import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.entity.ExhibitionHall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCExhibitionHallDao implements GenericDAO<ExhibitionHall> {

    private Connection connection;
    private ExhibitionHallMapper hallMapper;

    public JDBCExhibitionHallDao(Connection connection){
        this.connection = connection;
        this.hallMapper = ExhibitionHallMapper.getInstance();
    }

    @Override
    public void insert(ExhibitionHall hall) {
        String query =
                "insert into ExpositionProject.exhibitionHalls (name, information) values (?,?);";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,hall.getName());
            statement.setString(2,hall.getInformation());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExhibitionHall getById(int id) {
        String query = "SELECT * FROM ExpositionProject.exhibitionHalls where id = ?;";
        ResultSet resultSet;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
             resultSet = statement.executeQuery();
             while (resultSet.next()){
                 return hallMapper.extractFromResultSet(resultSet);
             }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return null;
    }



    @Override
    public void update(ExhibitionHall hall) {
        String query = "update ExpositionProject.exhibitionHalls set name=?, information=? where id=?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getInformation());
            statement.setInt(3, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ExhibitionHall hall) {
        String query = "delete from ExpositionProject.exhibitionHalls where id = ?; ";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExhibitionHall> getAll() {
        List<ExhibitionHall> list = new ArrayList<>();
        ResultSet resultSet;
        String query = "select * from ExpositionProject.exhibitionHalls;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(hallMapper.extractFromResultSet(resultSet));
            }
            return list;
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
