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

    public JDBCTicketDao(Connection connection) {
        this.connection = connection;
        this.userMapper = UserMapper.getInstance();
        this.expoMapper = ExpositionMapper.getInstance();
        this.hallMapper = ExhibitionHallMapper.getInstance();
    }

    @Override
    public void insert(Ticket ticket) {
        String query = "insert into ExpositionProject.tickets (user_id, exposition_id, count) values (?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, ticket.getUser().getId());
            statement.setInt(2, ticket.getExposition().getId());
            statement.setInt(3, ticket.getCount());
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
            if (resultSet.next()){
                User user = userMapper.extractFromResultSet(resultSet);
                Exposition expo = expoMapper.extractFromResultSet(resultSet);
                expo.setHall(hallMapper.extractFromResultSet(resultSet));
                return new Ticket.Builder()
                        .setId(resultSet.getInt("tickets.id"))
                        .setCount(resultSet.getInt("tickets.id"))
                        .setUser(user)
                        .setExposition(expo)
                        .build();
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

    public List<Ticket> getUserTickets(User user){
        List<Ticket> ticketList = new ArrayList<>();
        Map<Integer, Exposition> expoMap = new HashMap<>();
        Map<Integer, ExhibitionHall> hallMap = new HashMap<>();
        Exposition expoTemp;
        ExhibitionHall hallTemp;
        int count;

        ResultSet resultSet;
        String query = "SELECT  user_id, exposition_id, sum(count) as count, users.*, expositions.*, exhibitionHalls.* " +
                "FROM ExpositionProject.tickets join users on tickets.user_id = users.id " +
                "join expositions on tickets.exposition_id = expositions.id " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where tickets.user_id = ? " +
                "group by exposition_id ;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                expoTemp = expoMapper.extractFromResultSet(resultSet);
                hallTemp = hallMapper.extractFromResultSet(resultSet);
                hallTemp = hallMapper.makeUnique(hallMap, hallTemp);
                expoTemp = expoMapper.makeUnique(expoMap, expoTemp);
                expoTemp.setHall(hallTemp);
                count = resultSet.getInt("count");
                System.out.println(count + " count of tickets");
                ticketList.add(new Ticket.Builder()
                        .setId(-1)
                        .setCount(count)
                        .setUser(user)
                        .setExposition(expoTemp)
                        .build());
            }
            return ticketList;
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

        ResultSet resultSet;

        String query = "SELECT * FROM ExpositionProject.tickets " +
                "join users on tickets.user_id = users.id " +
                "join expositions on tickets.exposition_id = expositions.id " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                //todo separate method
                user = userMapper.extractFromResultSet(resultSet);
                expo = expoMapper.extractFromResultSet(resultSet);
                hall = hallMapper.extractFromResultSet(resultSet);

                user = userMapper.makeUnique(usersMap, user);
                hall = hallMapper.makeUnique(hallMap, hall);
                expo = expoMapper.makeUnique(expoMap, expo);
                expo.setHall(hall);
                Ticket.Builder builder = new Ticket.Builder();
                builder.setId(resultSet.getInt("tickets.id"))
                        .setCount(resultSet.getInt("tickets.count"))
                        .setUser(user)
                        .setExposition(expo);
                ticketList.add(builder.build());
            }
            return ticketList;
        } catch (SQLException e){
            //todo log
            throw new RuntimeException(e);
        }
    }
}
