package Helpers;

public class Validator {

    public static boolean isPrice(String value){
        try {
            int integerValue = Integer.parseInt(value);
            if(integerValue < 0){
                return false;
            }
            return true; 
        } catch (NumberFormatException e) {
            // Return false if the value is not a valid integer
            return false;
        }
    }

    public static boolean isEmail(String value){
            try{
                String regex = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|hotmail\\.com|outlook\\.com)$";
                return value.matches(regex);
            }catch(NullPointerException e){
                return false;
            }
    }

    public static boolean isPhoneNumber(String value){
        try {
            if (value == null || value.isEmpty()) {
                return false; // إذا كانت null أو فارغة، اعتبرها غير صالحة
            }
             // Regex للتحقق من رقم الهاتف
            String regex = "^(07|06|05)[0-9]{8}$";
            // تحقق إذا كان النص يطابق الصيغة
            return value.matches(regex);
        } catch (NumberFormatException e) {
            // Return false if the value is not a valid integer
            return false;
        }
    }
}
