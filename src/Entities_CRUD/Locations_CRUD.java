package Entities_CRUD;

import Helpers.DbOperation;
import java.sql.*;
import javax.swing.JOptionPane;

public class Locations_CRUD {
    private static  DbOperation db = new DbOperation();

    public static ResultSet GetAll(){

        ResultSet data = db.GetData("reservation.id_reservation, client.nom, vehicule.modele, reservation.statut, CAST(COALESCE(SUM(paiement.montant), 0) AS  SIGNED) AS montant_total, reservation.date_debut, reservation.date_fin, reservation.id_client, reservation.id_vehicule", "reservation","INNER JOIN client ON reservation.id_client = client.id_client \r\n" + //
                        "INNER JOIN vehicule ON reservation.id_vehicule = vehicule.id_vehicule \r\n" + //
                        "LEFT JOIN paiement ON reservation.id_reservation = paiement.id_reservation \r\n" + //
                        "GROUP BY reservation.id_reservation, client.nom, vehicule.modele, reservation.statut, reservation.date_debut, reservation.date_fin, reservation.id_client, reservation.id_vehicule;");
        return data;
    }


    public static boolean Insert(String columns, String values) {
        return db.Insert("reservation", columns, values);
    }
    public static boolean Delete(String tableName, String condition) {
        return db.Delete(tableName, condition);
    }
    public static int GetLastReservationId() {
          // الحصول على آخر id_reservation
         try{

            ResultSet data = db.GetData("MAX(id_reservation)", "reservation");
        
            if (data != null && data.next()) {
                return data.getInt(1); // إرجاع القيمة من العمود الأول
            } else {
                return -1; // في حال لم توجد بيانات، إرجاع -1
            }
         }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"honak khataa");
            return -1;

         }
          }

    public static void UpdateStatus(int id_reservation,String statut){
        db.Update("reservation","statut='" + statut + "'","id_reservation=" + id_reservation);
    }

    public static String IsExsists(String email,String Password){
        return db.isExists(email, Password);
    }
}
