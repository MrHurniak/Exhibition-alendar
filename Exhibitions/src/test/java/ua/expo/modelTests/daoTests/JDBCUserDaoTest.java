package ua.expo.modelTests.daoTests;

import org.junit.BeforeClass;
import ua.expo.model.dao.DaoFactory;
import ua.expo.model.dao.impl.JDBCUserDao;

public class jDBCUserDaoTest {

    private static JDBCUserDao userDao;

    @BeforeClass
    public static void init() {
        userDao = DaoFactory.getInstance().createUserDao();
    }


}
