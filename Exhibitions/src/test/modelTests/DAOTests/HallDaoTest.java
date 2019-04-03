package modelTests.DAOTests;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.impl.JDBCExhibitionHallDao;
import ua.training.model.entity.ExhibitionHall;

public class HallDaoTest {
    private static JDBCExhibitionHallDao hallDao;

    @BeforeClass
    public static void init(){
        hallDao = DaoFactory.getInstance().createExhibitionHallDao();
    }

    @Test
    public void getAllTest(){
        hallDao.getAll().forEach(System.out::println);
    }

    @Test
    public void insertTest(){
        ExhibitionHall hall = new ExhibitionHall();
        hall.setName("Test name");
        hall.setInformation("Test information");

        hallDao.insert(hall);

        hallDao.getAll().forEach(System.out::println);
    }

    @Test
    public void getByIdTest(){
        ExhibitionHall hall = hallDao.getById(2);

        System.out.println(hall);
    }

    @Test
    public void updateTest(){
        ExhibitionHall hall = new ExhibitionHall();
        hall.setId(3);
        hall.setName("New test name");
        hall.setInformation("New test information");
        hallDao.update(hall);

        hallDao.getAll().forEach(System.out::println);
    }

    @Test
    public void deleteTest(){
        ExhibitionHall hall = new ExhibitionHall();
        hall.setId(3);
        hallDao.delete(hall);

        hallDao.getAll().forEach(System.out::println);
    }

}
