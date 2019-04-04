package propertiesTests;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class EncodingTest {
    @Test
    public void firstTest() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale("ua", "UA"));
        System.out.println(resourceBundle.getString("header.exposition"));
    }

}
