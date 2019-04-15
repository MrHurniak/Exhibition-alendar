package ua.training.model.dao.impl;

import org.apache.log4j.Logger;
import ua.training.model.dao.GenericDAO;
import ua.training.model.dao.mapper.ExhibitionHallMapper;
import ua.training.model.dao.mapper.ExpositionMapper;
import ua.training.model.entity.ExhibitionHall;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.enums.ExpositionStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCExpositionDao implements GenericDAO<Exposition> {

    private final static Logger LOGGER = Logger.getLogger(JDBCExpositionDao.class);

    private DataSource dataSource;
    private ExpositionMapper expoMapper;
    private ExhibitionHallMapper hallMapper;

    JDBCExpositionDao(DataSource dataSource){
        this.dataSource = dataSource;
        this.expoMapper = ExpositionMapper.getInstance();
        this.hallMapper = ExhibitionHallMapper.getInstance();
        LOGGER.debug("Creating instance of " + this.getClass().getName());
    }

    @Override
    public void insert(Exposition expo) {
        String query = "insert into ExpositionProject.expositions (theme, shortDescription, fullDescription, " +
                "price, date, date_to, hall_id, state) values (?, ?, ?, ?, ?, ?, ?, ?);";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getPrice());
            statement.setDate(5, expo.getDate());
            statement.setDate(6, expo.getDate_to());
            statement.setInt(7, expo.getHall().getId());
            statement.setString(8, expo.getExpositionStatus().name());
            statement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error("SQLException while insert Exposition instance with theme=" + expo.getTheme(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Inserted new exposition with theme=" + expo.getTheme());
    }


    @Override
    public Exposition getById(int id) {
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.id = ?;";
        ResultSet resultSet;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            LOGGER.info("Successful execution of select query by exposition id=" + id);
            if (resultSet.next()){
                Exposition expo = expoMapper.extractFromResultSet(resultSet);
                expo.setHall(hallMapper.extractFromResultSet(resultSet));
                return expo;
            }
        } catch (SQLException e){
            LOGGER.error("SQLException while get ExhibitionHall instance by id=" + id, e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(Exposition expo) {
        String query = "update ExpositionProject.expositions  set theme = ?, shortDescription = ?, " +
                "fullDescription = ?, price = ?,date = ?,date_to = ?, hall_id = ?, state=? where id = ?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, expo.getTheme());
            statement.setString(2, expo.getShortDescription());
            statement.setString(3, expo.getFullDescription());
            statement.setInt(4, expo.getPrice());
            statement.setInt(5, expo.getHall().getId());
            statement.setDate(6, expo.getDate());
            statement.setDate(7, expo.getDate_to());
            statement.setString(8, expo.getExpositionStatus().name());
            statement.setInt(9, expo.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error("SQLException while trying update exposition with id=" + expo.getId(), e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Update exposition with id=" + expo.getId() + ", theme=" + expo.getTheme());
    }

    public void saveDelete(Exposition expo){
        String query = "update ExpositionProject.expositions  set state='DELETED' where id = ?;";
        LOGGER.info("Request to save 'delete' of exposition with id=" + expo.getId());
        operationById(expo.getId(), query);
    }

    @Override
    public void delete(Exposition expo) {
        String query = "delete from ExpositionProject.expositions where id = ?;";
        LOGGER.info("Request to delete of exposition with id=" + expo.getId());
        operationById(expo.getId(), query);
    }

    private void operationById(int expoId, String query){
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, expoId);
            statement.executeUpdate();
        } catch (SQLException e){
            LOGGER.error("SQLException trying to perform operation under exposition with id=" + expoId, e);
            throw new RuntimeException(e);
        }
        LOGGER.info("Performed query='"+query+"' for exposition with id=" + expoId);
    }
    @Override
    public List<Exposition> getAll() {
           String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Trying to get all expositions");
            return getList(statement);
        }catch (SQLException e){
            LOGGER.error("SQLException while get all exposition", e);
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getAllNotDeleted(){
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.state != 'DELETED';";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            LOGGER.info("Trying to get all expositions where state are not 'deleted'");
            return getList(statement);
        }catch (SQLException e){
            LOGGER.error("SQLException while get all exposition where state ane not 'deleted'", e);
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getAllByStatus(ExpositionStatus status){
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.state=?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status.name());
            LOGGER.info("Trying to get all expositions with status " + status.name());
            return getList(statement);
        }catch (SQLException e){
            LOGGER.error("SQLException while get all exposition with status "
                    + status.name(), e);
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getInRange(int offset, int length){
        String query = "SELECT * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.state != 'DELETED' limit ?,?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, offset);
            statement.setInt(2, length);
            LOGGER.info("Request to get exposition in range offset="
                    + offset + " length=" + length);
            return getList(statement);
        } catch (SQLException e){
            LOGGER.error("SQLException while get expositions in range offset="
                    + offset + " length=" + length, e);
            throw new RuntimeException(e);
        }
    }

    public List<Exposition> getInRangeHall(int offset, int length, int hallId) {
        String query = "SELECT  * FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.hall_id = ? AND expositions.state != 'DELETED' " +
                "limit ?,?;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, hallId);
            statement.setInt(2, offset);
            statement.setInt(3, length);
            LOGGER.info("Request to get exposition in range offset="
                    + offset + " length=" + length + " hall id=" + hallId);
            return getList(statement);
        } catch (SQLException e){
            LOGGER.error("SQLException while get expositions in range offset="
                    + offset + " length=" + length + "hall id=" + hallId, e);
            throw new RuntimeException(e);
        }
    }


    public int getNumberRows(int id) {
        String query = "SELECT count(expositions.id) as count FROM ExpositionProject.expositions " +
                "join exhibitionHalls on expositions.hall_id = exhibitionHalls.id " +
                "where expositions.hall_id = ? AND expositions.state != 'DELETED';";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("Performed request to get count of rows in expositions table where hall id=" + id);
            if (resultSet.next()){
                return resultSet.getInt("count");
            }

        } catch (SQLException e){
            LOGGER.error("SQLException while get rows count in expositions table where hall id=" + id, e);
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int getNumberRows() {
        String query = "SELECT count(expositions.id) as count FROM ExpositionProject.expositions " +
                "WHERE expositions.state != 'DELETED';";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            LOGGER.info("Performed request to get count of rows in expositions table");
            if (resultSet.next()){
                return resultSet.getInt("count");
            }
        } catch (SQLException e){
            LOGGER.error("SQLException while get rows count in expositions table", e);
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
