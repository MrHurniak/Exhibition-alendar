package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.service.util.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private JDBCUserDao userDao;
    private JDBCTicketDao ticketDao;

    public UserService(){
        userDao = DaoFactory.getInstance().createUserDao();
        ticketDao = DaoFactory.getInstance().createTicketDao();
    }
    public void createUser(User user){
        userDao.insert(user);
    }

    //todo only Exposition return
    public List<Exposition> getUserTickets(User user){
        return ticketDao.getUserExposition(user);
    }

    public void buyTickets(User user, Exposition exposition, int tickets){
        if(tickets > 0 && tickets < 26) {
            System.out.println("Buy " + tickets + " tickets.");
            for (int i = 0; i < tickets; i++) {
                ticketDao.insert(new Ticket(user, exposition));
            }
        }
    }

    public Optional<User> getByLogin(String login){
        if(login == null){
            return Optional.empty();
        }
        return Optional.ofNullable(userDao.getByLogin(login));
    }

    public boolean validateData(User user) {
        //todo validate
        return true;
    }
}
