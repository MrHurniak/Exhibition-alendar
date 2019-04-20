package modelTests.serviceTests;

import org.junit.Assert;
import org.junit.Test;
import ua.training.model.service.util.HashingPasswordUtil;

public class MD5UtilTest {
    private HashingPasswordUtil passwordUtil = new HashingPasswordUtil();
    @Test
    public void encryptingMD5Test() {
        String password = "password";
        String hash = passwordUtil.encryptionMD5(password);
        System.out.println(hash);
    }

    @Test
    public void equalsTest() {
        String hash = "5F4DCC3B5AA765D61D8327DEB882CF99";
        String password = "password";
        String newHash = passwordUtil.encryptionMD5(password);
        Assert.assertEquals(hash, newHash);
    }

    @Test
    public void encryptingSHA256Test(){
        String password = "1";
        String newHash = passwordUtil.encryptionSHA256(password);
        System.out.println(newHash);
    }
}
