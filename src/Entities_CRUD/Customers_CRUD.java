package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;

public class Customers_CRUD {
    private static  DbOperation db = new DbOperation();
    public static ResultSet GetData() {
        return db.GetData("*", "client");
    }

    //add customer function
    public static boolean Insert(String columns, String values) {
        return db.Insert("client", columns, values);
    }
    public static void Update(int id_client, String nom, String prenom, String telephone, String email, String numero_permis, String sex) {
    String updateQuery = "nom='" + nom + "', " +
                         "prenom='" + prenom + "', " +
                         "telephone='" + telephone + "', " +
                         "email='" + email + "', " +
                         "numero_permis='" + numero_permis + "', " +
                         "sex='" + sex + "'";
                         
    // تنفيذ التحديث
    db.Update("client", updateQuery, "id_client=" + id_client);
}

    
    public static boolean Delete(String tableName, String condition) {
        return db.Delete(tableName, condition);
    }
    public static ResultSet GetFilteredData(String searchText) {
        String condition = "1=1";  // Default: no filter, show all records
    
       
    
        // Filter by search text (if provided)
        if (!searchText.isEmpty()) {
            condition += " AND (marque LIKE '%" + searchText + "%' OR modele LIKE '%" + searchText + "%')";
        }
    
        return db.GetFilltredData("*", "vehicule", condition);
    }


    
}
