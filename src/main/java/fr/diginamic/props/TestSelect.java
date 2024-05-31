package fr.diginamic.props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.ArrayList;
public class TestSelect {
    
    public static void main(String[] args) {
        ResourceBundle config = ResourceBundle.getBundle("config");
        String url = config.getString("database.url");
        String user = config.getString("database.user");
        String pwd = config.getString("database.password");
        
        Connection conn = null;
        Statement stmt1 = null;
        
        ArrayList<Fournisseur> liste = new ArrayList<Fournisseur>();

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(url, user, pwd);

            String query1 = "SELECT * FROM fournisseur";
            stmt1 = conn.createStatement();
            ResultSet result = stmt1.executeQuery(query1);
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                Fournisseur fournisseur = new Fournisseur(id, nom); 
                liste.add(fournisseur); 
                System.out.println(fournisseur);
            }
            
            result.close();
            stmt1.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
