package Entities_CRUD;


import Helpers.DbOperation;

public class AdminLoginPage_CRUD {
    private static  DbOperation db = new DbOperation();

    public static String IsExsists(String email,String Password){
        return db.isExists(email, Password);
    }
    
}
