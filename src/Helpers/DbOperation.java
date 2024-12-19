package Helpers;

import Config.Database;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class DbOperation {

    private Connection conn;
    private String query = "";
    private String lastError = ""; // لتخزين آخر رسالة خطأ

    public DbOperation() {
        Database db = new Database();
        try {
            conn = db.GetConnection();
            if (conn == null || conn.isClosed()) {
                lastError = "Échec de la connexion à la base de données.";
            }
        } catch (Exception e) {
            lastError = e.getMessage();
            e.printStackTrace();
        }
    }

    // دالة لاختبار الاتصال بقاعدة البيانات
    public boolean testConnection() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            lastError = e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    // دالة للحصول على آخر خطأ
    public String getLastError() {
        return lastError;
    }

    public ResultSet GetData(String columns, String tableName) {
        try {
            query = "SELECT " + columns + " FROM " + tableName;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null,
                    "Une erreur s'est produite lors de la récupération des données : " + e.getMessage(), "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    

    public ResultSet GetSpecialData(String query)
    {
        try 
        {
            
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la récupération des données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public int countrows(String colom,String value, ResultSet rs) {
    int count = 0;
    
    try {
        
        while (rs.next()) {
            if (rs.getString(colom).equals(value)) {
                count++;
            }
        }
    } catch (SQLException e) {
        // Handle the SQLException
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }
    return count;
    }

    public int countsex(String colom,String value, ResultSet rs) {
        int count = 0;
        
        try {
            
            while (rs.next()) {
                if (rs.getString(colom).equals(value)) {
                    count=rs.getInt("ReservationCount");
                }
            }
        } catch (SQLException e) {
            // Handle the SQLException
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
        }
   

    public ResultSet GetFilltredData(String columns, String tableName, String condition) {
        try {
            query = "SELECT " + columns + " FROM " + tableName + " WHERE " + condition;
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane
                    .showMessageDialog(
                            null, "Une erreur s'est produite lors de la récupération des données \nSelect phrase: "
                                    + query + "\nerror message: " + e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    

    public String isExists(String email, String password) {
        String query = "SELECT role FROM utilisateur WHERE email = ? AND mot_de_passe = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean Insert(String tableName, String columns, String values) {
        try {
            query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la insertion des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean Update(String tableName, String columnsAndValues, String condition) {
        try {
            query = "UPDATE " + tableName + " SET " + columnsAndValues + " WHERE " + condition;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la mise à jour des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean Delete(String tableName, String condition) {
        try {
            query = "DELETE FROM " + tableName + " WHERE " + condition;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            lastError = e.getMessage();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la suppression des données : \n"
                    + "query: " + query + "\n error message: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ResultSet GetData(String columns,String tableName,String JoinQuery)
    {
        try 
        {
            query = "SELECT " + columns + " FROM " + tableName + " " + JoinQuery;
           
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la récupération des données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

   
    public String[] getValueFromRow(ResultSet rs,String colum) {
        String[] value = new String[5];
        int i=0;
        try {
            // Move the cursor to the third row
            while (rs.next()&& i<5) {
               value[i]=rs.getString(colum);
               i++;
            }
        } catch (SQLException e) {
            // Handle the SQLException
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return value;
    }
    
    public int  getTotalpaiment(ResultSet rs,String type) {
        int value = 0;
        
        try {
            // Move the cursor to the third row
            while (rs.next()) {
                if(rs.getString("type").equals(type))  
                value=value+rs.getInt("montant");
                
              
            }
        } catch (SQLException e) {
            // Handle the SQLException
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return value;
    }
    public double[] getSpecialValueFromRow(ResultSet rs, String colum) {
    double[] frequency = new double[5];
    int i = 0;
    double total = 0;

    try {
        // Populate the frequency array and calculate the total
        while (rs.next()) {
            if (i < 5) {
                double value = rs.getDouble(colum);

                // Round each value to two decimal places before storing
                frequency[i] = new BigDecimal(value)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue();
                total += value;
                i++;
            }
        }

        // Reset index to process percentages
        i = 0;

        // Compute percentages and round to two decimal places
        while (i < 5) {
            double value = frequency[i];
            frequency[i] = new BigDecimal((value / total) * 100)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue();
            i++;
        }
    } catch (SQLException e) {
        // Handle the SQLException
        System.err.println("SQL Exception: " + e.getMessage());
        e.printStackTrace();
    }

    return frequency;
}
    public long [] countdays(ResultSet rs){
        long days[]=new long[5];
        int i=0;
    try {
        while (rs.next() && i<5) {
            ResultSet rs2=GetSpecialData("SELECT date_debut, date_fin FROM reservation WHERE id_vehicule ="+rs.getInt("id_vehicule") +";");
                                while (rs2.next()) {
                                     // Parse the input dates
        LocalDate start =rs2.getDate("date_debut").toLocalDate(); // Example format: "2024-12-01"
        LocalDate end = rs2.getDate("date_fin").toLocalDate();

        // Calculate the number of days between the two dates
        days[i] = days[i]+ChronoUnit.DAYS.between(start, end);

                                }
            i++;
        }
    } catch (SQLException e) {
        System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
    }

        return days;
    }
}
