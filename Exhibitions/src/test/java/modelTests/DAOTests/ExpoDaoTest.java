package modelTests.DAOTests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.expo.model.dao.DaoFactory;
import ua.expo.model.dao.impl.JDBCExpositionDao;
import ua.expo.model.entity.ExhibitionHall;
import ua.expo.model.entity.Exposition;

import java.util.List;

public class ExpoDaoTest {
    private static JDBCExpositionDao expoDao;
    @BeforeClass
    public static void init(){
        expoDao = DaoFactory.getInstance().createExpositionDao();
    }

    @Test
    public void getByIdTest(){
        Exposition expo = expoDao.getById(1);
        System.out.println(expo);
    }

    @Test
    public void getAllTest(){
        List<Exposition> list = expoDao.getAll();
        list.forEach(System.out::println);
        Assert.assertTrue(list.get(0).getHall()==list.get(2).getHall());
        Assert.assertTrue(list.get(4).getHall()==list.get(2).getHall());
        Assert.assertTrue(list.get(0).getHall()==list.get(4).getHall());
        Assert.assertTrue(list.get(1).getHall()==list.get(3).getHall());
        Assert.assertFalse(list.get(0).getHall()==list.get(1).getHall());
    }

    @Test
    public void insertTest(){
        String testV = "insertTest";
        Exposition expo = new Exposition(0, 100, 100
                , testV, testV, testV, new ExhibitionHall(1,testV,testV));
        expoDao.insert(expo);
        List<Exposition> list = expoDao.getAll();
        list.forEach(System.out::println);
    }

    @Test
    public void deleteTest(){
        Exposition expo = new Exposition();
        expo.setId(6);
        expoDao.delete(expo);
        List<Exposition> list = expoDao.getAll();
        list.forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        Exposition expo = expoDao.getById(5);
        expo.setFullDescription("Update test description.");
        expoDao.update(expo);
        List<Exposition> list = expoDao.getAll();
        list.forEach(System.out::println);

    }
}
