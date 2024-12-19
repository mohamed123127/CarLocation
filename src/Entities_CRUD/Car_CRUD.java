package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Car_CRUD {
    private static  DbOperation db = new DbOperation();

    public static void UpdateStatus(int id_car,String statut){
        db.Update("vehicule","etat='" + statut + "'","id_vehicule=" + id_car);
    }
    
    public static void Update(int id_car, String marque, String modele, int annee, String type, String carburant, int prix_location_jour) {
        String updateQuery = "marque='" + marque + "', " +
                         "modele='" + modele + "', " +
                         "annee=" + annee + ", " +
                         "type='" + type + "', " +
                         "carburant='" + carburant + "', " +
                         "prix_location_jour=" + prix_location_jour ;    
    // تنفيذ التحديث
    db.Update("vehicule", updateQuery, "id_vehicule=" + id_car);
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


    public static ResultSet GetFilteredData(String searchText) {
        String condition = "1=1";  // Default: no filter, show all records
    
       
    
        // Filter by search text (if provided)
        if (!searchText.isEmpty()) {
            condition += " AND (marque LIKE '%" + searchText + "%' OR modele LIKE '%" + searchText + "%')";
        }
    
        return db.GetFilltredData("*", "vehicule", condition);
    }

    
    public static int getLocationPrice(int id_car) {
        try {
            ResultSet resultSet = db.GetFilltredData("prix_location_jour", "vehicule", "id_vehicule = " + id_car);
            if (resultSet.next()) {
                return resultSet.getInt("prix_location_jour");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; 
    }

}
