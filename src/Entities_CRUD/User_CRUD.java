package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;

public class User_CRUD {
    private static  DbOperation db = new DbOperation();
    public static ResultSet GetData() {
        return db.GetData("*", "utilisateur");
    }

    //add customer function
    public static boolean Insert(String columns, String values) {
        return db.Insert("utilisateur", columns, values);
    }

    public static void Update(int id, String nom, String prenom, String email, String mot_de_passe, String role) {
    String updateQuery = "nom='" + nom + "', " +
                         "prenom='" + prenom + "', " +
                         "email='" + email + "', " +
                         "mot_de_passe='" + mot_de_passe + "', " +
                         "role='" + role + "'";
                         
    // تنفيذ التحديث
    db.Update("utilisateur", updateQuery, "id=" + id);
}

    
    public static boolean Delete(String tableName, String condition) {
        return db.Delete(tableName, condition);
    }
        public static ResultSet GetFilteredData(String searchText, String category) {
        String condition = "1=1";  // Default: no filter, show all records
    
        // Filter by category (if selected)
        if (!category.equals("Tous")) {
            condition += " AND type = '" + category + "'";
        }
    
        // Filter by search text (if provided)
        if (!searchText.isEmpty()) {
            condition += " AND (marque LIKE '%" + searchText + "%' OR modele LIKE '%" + searchText + "%')";
        }
    
        return db.GetFilltredData("*", "vehicule", condition);
    }

    
}
