package ua.training.model.dao.impl;

import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTicketDao implements GenericDAO<Ticket> {
    private Connection connection;
    private UserMapper userMapper;
    private ExpositionMapper expoMapper;
    private ExhibitionHallMapper hallMapper;

    public JDBCTicketDao(Connection connection,
                         UserMapper userMapper, ExpositionMapper expoMapper) {
        this.connection = connection;
        this.userMapper = userMapper;
        this.expoMapper = expoMapper;
        this.hallMapper = expoMapper.getHallMapper();
    }

    @Override
    public void insert(Ticket ticket) {
        String query = "insert into ExpositionProject.tickets (user_id, exposition_id) values (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getExposition().getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public Ticket getById(int id) {
        String query = "SELECT * FROM ExpositionProject.tickets " +
                "join users on tickets.user_id = users.id " +
                "join expositions on tickets.exposition_id = expositions.id " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where tickets.id = ?;";
        ResultSet resultSet;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                //todo separate method
                User user = userMapper.extractFromResultSet(resultSet);
                Exposition expo = expoMapper.extractFromResultSet(resultSet);
                expo.setHall(hallMapper.extractFromResultSet(resultSet));
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getInt("tickets.id"));
                ticket.setUser(user);
                ticket.setExposition(expo);

                return ticket;
            }
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Ticket ticket) {
        String query = "update ExpositionProject.tickets set user_id = ?, exposition_id = ? where id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getExposition().getId());
            statement.setInt(3, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Ticket ticket) {
        String query = "delete from ExpositionProject.tickets where id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, ticket.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }

    //todo refactoring
    @Override
    public List<Ticket> getAll() {
        List<Ticket> ticketList = new ArrayList<>();

        Map<Integer, User> usersMap = new HashMap<>();
        Map<Integer, Exposition> expoMap = new HashMap<>();
        Map<Integer, ExhibitionHall> hallMap = new HashMap<>();

        User user;
        Exposition expo;
        ExhibitionHall hall;
        Ticket ticket;

        ResultSet resultSet;

        String query = "SELECT * FROM ExpositionProject.tickets " +
                "join users on tickets.user_id = users.id " +
                "join expositions on tickets.exposition_id = expositions.id " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                //todo separate method
                ticket = new Ticket();
                user = userMapper.extractFromResultSet(resultSet);
                expo = expoMapper.extractFromResultSet(resultSet);
                hall = hallMapper.extractFromResultSet(resultSet);

                user = userMapper.makeUnique(usersMap, user);
                hall = hallMapper.makeUnique(hallMap, hall);
                expo = expoMapper.makeUnique(expoMap, expo);
                expo.setHall(hall);

                ticket.setId(resultSet.getInt("tickets.id"));
                ticket.setUser(user);
                ticket.setExposition(expo);
                ticketList.add(ticket);
            }
            return ticketList;
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
