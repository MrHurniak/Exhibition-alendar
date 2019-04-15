package ua.training.model.dao.impl;

import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.enums.HallStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCExhibitionHallDao implements GenericDAO<ExhibitionHall> {

    private DataSource dataSource;
    private ExhibitionHallMapper hallMapper;

    JDBCExhibitionHallDao(DataSource dataSource){
        this.dataSource = dataSource;
        this.hallMapper = ExhibitionHallMapper.getInstance();
    }

    @Override
    public void insert(ExhibitionHall hall) {
        String query =
                "insert into ExpositionProject.exhibitionHalls (name, information, state) values (?,?,?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,hall.getName());
            statement.setString(2,hall.getInformation());
            statement.setString(3, hall.getStatus().name());
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
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
             resultSet = statement.executeQuery();
             if (resultSet.next()){
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
        String query = "update ExpositionProject.exhibitionHalls set name=?, information=?, state=? where id=?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getInformation());
            statement.setString(3, hall.getStatus().name());
            statement.setInt(4, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    public void saveDelete(ExhibitionHall hall){
        setStatus(hall, HallStatus.DELETED);
    }

    public void setStatus(ExhibitionHall hall, HallStatus status){
        String query = "update ExpositionProject.exhibitionHalls set state=? where id=?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, status.name());
            statement.setInt(2, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ExhibitionHall hall) {
        String query = "delete from ExpositionProject.exhibitionHalls where id = ?; ";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, hall.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ExhibitionHall> getAll() {
        String query = "select * from ExpositionProject.exhibitionHalls;";
        return getList(query);
    }

    public List<ExhibitionHall> getAllOK(){
        String query = "select * from ExpositionProject.exhibitionHalls where exhibitionHalls.state='OK';";
        return getList(query);
    }

    private List<ExhibitionHall> getList(String query){
        List<ExhibitionHall> list = new ArrayList<>();
        ResultSet resultSet;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
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
