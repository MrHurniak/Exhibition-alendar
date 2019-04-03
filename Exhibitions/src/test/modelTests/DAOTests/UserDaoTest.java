package modelTests.DAOTests;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCUserDao;
import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.util.List;

public class UserDaoTest {
    private static JDBCUserDao userDao;

    @BeforeClass
    public static void unit(){
        userDao = DaoFactory.getInstance().createUserDao();
    }

    @Test
    public void getByIdTest(){
        User user = userDao.getById(1);
        System.out.println(user);
    }

    @Test
    public void getAllTest(){
        List<User> list = userDao.getAll();
        list.forEach(System.out::println);
    }

    @Test
    public void insertTest(){
        User user = new User();
        String smth = "TestFromTest2";
        user.setName(smth);
        user.setSurname(smth);
        user.setLogin(smth);
        user.setPassword(smth);
        user.setEmail(smth);
        user.setRole(Role.USER);
        userDao.insert(user);

        userDao.getAll().forEach(System.out::println);
    }

    @Test
    public void deleteTest(){
        User temp = new User();
        temp.setId(8);
        userDao.delete(temp);

        userDao.getAll().forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        User user = new User();
        String smth = "TestFromTestUpdate";
        user.setId(6);
        user.setName(smth);
        user.setSurname(smth);
        user.setLogin(smth);
        user.setPassword(smth);
        user.setEmail(smth);
        user.setRole(Role.USER);
        userDao.update(user);

        userDao.getAll().forEach(System.out::println);
    }
}
