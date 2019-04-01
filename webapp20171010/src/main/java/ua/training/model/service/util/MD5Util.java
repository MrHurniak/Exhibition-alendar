package ua.training.model.service.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public String encryption(String purpose){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(purpose.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e){
            //todo log
            e.printStackTrace();
        }
        return purpose;
    }
}
