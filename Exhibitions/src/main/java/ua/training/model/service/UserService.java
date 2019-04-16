package ua.training.model.service;

import org.apache.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;
import ua.training.model.service.util.HashingPasswordUtil;
import ua.training.model.service.util.RegexContainer;
import ua.training.model.service.util.Utils;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final static Logger LOGGER = Logger.getLogger(UserService.class);

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
        LOGGER.info("Request to create new user with login=" + login);
        if(!Utils.isNotNull(name, surname, email, login, password)){
            //todo log
            throw new IllegalArgumentException("All parameters must be filled!");
        }

        User.Builder builder = new User.Builder();
        User user = builder.setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setRole(Role.USER)
                .setLogin(login)
                .setPassword(hashingUtil.encryptionSHA256(password))
                .build();
        if(validateData(user)) {
            LOGGER.info("User data is valid. Make request to user dao.");
            userDao.insert(user);
            return;
        }
        LOGGER.info("Users data is not correct.");
        throw new IllegalArgumentException("Please, check all date and correct it.");
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

    public boolean checkPassword(User user, String password) {
        return hashingUtil.isEqualsSHA256(password, user.getPassword());
    }

    private boolean validateData(User user) {
        if(!Utils.regexCheck(user.getName(), RegexContainer.NAME_RG)){
            return false;
        }
        if(!Utils.regexCheck(user.getSurname(), RegexContainer.SURNAME_RG)){
            return false;
        }
        return user.getLogin().length() >= 5 && user.getPassword().length() >= 7;
    }


}
