package Entities_CRUD;

import Helpers.DbOperation;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import Helpers.DbOperation;


public class Retour_CRUD {
    
    public static void add(int reservitionId,String EtatRetour){
        DbOperation db = new DbOperation();
        boolean isSuccessful = db.Insert("retour", "id_reservation,date_retour,etat_retour", String.format("%d,'%s','%s'",reservitionId,LocalDate.now().toString(),EtatRetour));
        if(isSuccessful){
            JOptionPane.showMessageDialog(null, "La voiture a été retournée avec succès","succès",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors du retour de la voiture","Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
}
