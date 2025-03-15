package com.monprojet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {
    String url = "jdbc:mysql://localhost:3306/monprojetjava";  // Mise à jour de la base
    String utilisateur = "root";
    String motDePasse = "";  // Le mot de passe est vide par défaut sur XAMPP
    Connection connexion = null;

    public Connexion() {
        try {
            // Établir la connexion
            this.connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie à la base monprojetjava !");
        } 
        
        catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }

    public void close() {
        if (this.connexion != null) {
            try {
                this.connexion.close();
                System.out.println("Connexion fermée avec succès.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
