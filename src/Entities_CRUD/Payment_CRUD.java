package Entities_CRUD;

import Helpers.DbOperation;
import java.time.LocalDate;

public class Payment_CRUD {

    private static DbOperation db = new DbOperation();

    // Modify montant to double (DECIMAL(10,2) equivalent)
    public static boolean Add(int id_reservation, String type_payment, double montant, String mode_paiement, String date_paiement) {
        // Format the montant to 2 decimal places
        String montantFormatted = String.format("%d", (int) montant);
        boolean isSuccessful = db.Insert("paiement", 
            "id_reservation,type_payment,montant,date_paiement,mode_paiement", 
            String.format("%d,'%s',%s,'%s','%s'", id_reservation, type_payment, montantFormatted, date_paiement, mode_paiement)
        );
        return isSuccessful;
    }

    public static boolean AddFraisDammage(int id_reservation, double damageFees, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Frais de dommages", damageFees, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }

    public static boolean AddlateReturnFees(int id_reservation, double lateReturnFees, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Frais de retard", lateReturnFees, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }

    public static boolean addLocationPayment(int id_reservation, double montant, String mode_paiement) {
        boolean isSuccessful = Add(id_reservation, "Paiement de location", montant, mode_paiement, LocalDate.now().toString());
        return isSuccessful;
    }

}
