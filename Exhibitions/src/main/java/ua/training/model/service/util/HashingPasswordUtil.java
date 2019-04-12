package ua.training.model.service.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingPasswordUtil {
    public String encryptionMD5(String purpose){
        return encrypt(purpose,"MD5");
    }

    public String encryptionSHA256(String purpose){
        return encrypt(purpose, "SHA-256");
    }

    private String encrypt(String purpose, String hash){
        try {
            MessageDigest md = MessageDigest.getInstance(hash);
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
