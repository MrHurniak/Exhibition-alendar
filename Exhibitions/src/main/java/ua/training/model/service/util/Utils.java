package ua.training.model.service.util;

public class Utils {
    public static boolean isNumber(String number){
        if(number == null){
            return false;
        }
        return number.matches("^([+\\-])?\\d+$");
    }
    public static boolean isNotNull(String ... strings){
        for (String string : strings) {
            if(string == null){
                return false;
            }
        }
        return true;
    }
}
