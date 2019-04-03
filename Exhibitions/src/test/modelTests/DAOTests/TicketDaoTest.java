package modelTests.DAOTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCTicketDao;
import ua.training.model.entity.Exposition;
import ua.training.model.entity.Ticket;
import ua.training.model.entity.User;

import java.util.List;

public class TicketDaoTest {
    private static JDBCTicketDao ticketDao;

    @BeforeClass
    public static void init(){
        ticketDao = DaoFactory.getInstance().createTicketDao();
    }

    @Test
    public void getByIdTest(){
        Ticket ticket = ticketDao.getById(1);
        System.out.println(ticket);
    }

    @Test
    public void getAllTest(){
        List<Ticket> list = ticketDao.getAll();
        Assert.assertSame(list.get(0).getUser(), list.get(1).getUser());
        Assert.assertSame(list.get(0).getExposition(),list.get(2).getExposition());
        Assert.assertSame(list.get(0).getExposition().getHall(),list.get(2).getExposition().getHall());
        list.forEach(System.out::println);
    }

    @Test
    public void insertTest(){
        User user = new User();
        Exposition exposition = new Exposition();
        user.setId(1);
        exposition.setId(1);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setExposition(exposition);
        ticketDao.insert(ticket);

        List<Ticket> list = ticketDao.getAll();
        list.forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        Ticket ticket = ticketDao.getById(5);
        ticket.getUser().setId(4);
        ticketDao.update(ticket);

        List<Ticket> list = ticketDao.getAll();
        list.forEach(System.out::println);
    }

    @Test
    public void deleteTest(){
        Ticket ticket = ticketDao.getById(6);
        ticketDao.delete(ticket);

        List<Ticket> list = ticketDao.getAll();
        list.forEach(System.out::println);
    }


}
