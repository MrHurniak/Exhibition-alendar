package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.exceptions.NotUniqLoginException;
import ua.training.model.service.util.HashingPasswordUtil;
import ua.training.model.service.util.Utils;

import java.util.List;
import java.util.Optional;

public class UserService {
    private static volatile UserService instance;
    private JDBCUserDao userDao;
    private JDBCTicketDao ticketDao;
    private HashingPasswordUtil hashingUtil;

    private UserService(){
        userDao = DaoFactory.getInstance().createUserDao();
        ticketDao = DaoFactory.getInstance().createTicketDao();
        hashingUtil = new HashingPasswordUtil();
    }

    public static UserService getInstance(){
        if(instance == null){
            synchronized (UserService.class){
                if(instance == null){
                    instance = new UserService();
                }
            }
        }
        return instance;
    }


    public void createUser(String name, String surname, String email, String login, String password){
        User user = userDao.getByLogin(login);
        if(user != null){
            throw new NotUniqLoginException(login, name, surname, email);
        }
        if(!Utils.isNotNull(name, surname, email, login, password)){
            //todo log
            throw new IllegalArgumentException("All parameters must be filled!");
        }

        User.Builder builder = new User.Builder();
        builder.setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setRole(Role.USER)
                .setLogin(login)
                .setPassword(hashingUtil.encryptionSHA256(password));

        userDao.insert(builder.build());
    }

    public List<Ticket> getUserTickets(User user){
        return ticketDao.getUserTickets(user);
    }

    public void buyTickets(User user, Exposition exposition, int count){
            ticketDao.insert(new Ticket.Builder()
                    .setCount(count)
                    .setUser(user)
                    .setExposition(exposition)
                    .build());
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
