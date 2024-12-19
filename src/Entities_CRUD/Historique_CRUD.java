package Entities_CRUD;

import Helpers.DbOperation;
import java.time.LocalDate;
import javax.swing.JOptionPane; 

public class Historique_CRUD {

    public static void add(int clientId,int carId,String Status,int montentSupplimenter,int montentTotal,String dateDebut){
        DbOperation db = new DbOperation();
        boolean isSuccessful = db.Insert("historique", "client_Id,car_Id,état,montant_Supplémentaire,montant_total,date_debut,date_fin", String.format("%d,%d,'%s',%d,%d,'%s','" + LocalDate.now()+"'", clientId, carId,Status,montentSupplimenter,montentTotal,dateDebut));
        if(isSuccessful){
            JOptionPane.showMessageDialog(null, "La voiture a été retournée avec succès","succès",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors du retour de la voiture","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
}
