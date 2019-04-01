package modelTests.serviceTests;

import org.junit.Assert;
import org.junit.Test;
import ua.training.model.service.util.MD5Util;


import java.security.NoSuchAlgorithmException;

public class MD5UtilTest {
    private MD5Util md5 = new MD5Util();
    @Test
    public void encryptingTest() throws NoSuchAlgorithmException {
        String password = "password";
        String hash = md5.encryption(password);
        System.out.println(hash);
    }

    @Test
    public void isEqualsTest() throws NoSuchAlgorithmException{
        String hash = "5F4DCC3B5AA765D61D8327DEB882CF99";
        String password = "password";
        String newHash = md5.encryption(password);
        Assert.assertEquals(hash, newHash);
    }
}
