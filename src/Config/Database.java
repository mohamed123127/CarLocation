package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    private String serveur = "jdbc:mysql://localhost:3306/gestionlocationvehicules"; // تأكد من المنفذ الصحيح
    private String user = "root";
    private String password = "";

    // طريقة للحصول على الاتصال
    public Connection GetConnection() {
        try {
            // تحميل السائق
            Class.forName("com.mysql.cj.jdbc.Driver");

            // إنشاء الاتصال
            Connection conn = DriverManager.getConnection(serveur, user, password);
            return conn;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erreur : Le pilote JDBC n'a pas été trouvé ! \n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la connexion à la base de données : \n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la connexion à la base de données : \n" + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return null; // إعادة null إذا فشل الاتصال
    }
}
