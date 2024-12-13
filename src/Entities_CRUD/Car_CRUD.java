package Entities_CRUD;

import java.sql.ResultSet;

import Helpers.DbOperation;

public class Car_CRUD {
    private static  DbOperation db = new DbOperation();

    public static void UpdateStatus(int id_car,String statut){
        db.Update("vehicule","etat='" + statut + "'","id_vehicule=" + id_car);
    }
       public static ResultSet GetAll(){
        ResultSet data = db.GetData("*", "vehicule");
        return data;
    }
    
    public static Object[][] GetDataa(String[] columnNames, String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'GetDataa'");
    }

    public static boolean DeleteCarById(String carId) {
        try {
            String condition = "id_vehicule = " + carId;
            return db.Delete("vehicule", condition);  // Call the DbOperation delete method
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
