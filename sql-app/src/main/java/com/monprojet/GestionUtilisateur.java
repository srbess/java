package com.monprojet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class GestionUtilisateur {
    ArrayList listUser = new ArrayList<>();

    public void add(Connexion connect, Scanner sc) {
        sc.nextLine();
        System.out.println("Nom de l'utilisateur");
        String lastName = sc.nextLine();

        System.out.println("Prénom de l'utilisateur");
        String fistName = sc.nextLine();

        System.out.println("Email de l'utilisateur");
        String email = sc.nextLine();

        try {
            String sqlInsert = "INSERT INTO utilisateurs (prenom, nom, email) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsert = connect.connexion.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, fistName);
            pstmtInsert.setString(2, lastName);
            pstmtInsert.setString(3, email);

            int rowsAffected = pstmtInsert.executeUpdate();
            System.out.println("Insertion réussie, lignes affectées : " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    public void delete(Connexion connect, Scanner sc) {
        System.out.println("Entrez l'ID de l'utilisateur à supprimer :");
        int userId = sc.nextInt();

        try {
            String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmtDelete = connect.connexion.prepareStatement(sqlDelete);
            pstmtDelete.setInt(1, userId);

            int rowsAffected = pstmtDelete.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    public void list(Connexion connect) {
        try {
            String sqlSelect = "SELECT id, prenom, nom, email FROM utilisateurs";
            Statement stmt = connect.connexion.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelect);

            System.out.println("Liste des utilisateurs :");
            System.out.println("ID\tPrénom\tNom\t\tEmail");

            while (rs.next()) {
                int id = rs.getInt("id");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");
                String email = rs.getString("email");

                System.out.println(id + "\t" + prenom + "\t" + nom + "\t" + email);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    // Méthode de mise à jour de l'utilisateur
    public void update(Connexion connect, Scanner sc) {
        System.out.println("Entrez l'ID de l'utilisateur à modifier : ");
        int userId = sc.nextInt();
        sc.nextLine(); // Consommer le retour à la ligne

        try {
            // Vérifier si l'utilisateur existe
            String sqlCheck = "SELECT * FROM utilisateurs WHERE id = ?";
            PreparedStatement pstmtCheck = connect.connexion.prepareStatement(sqlCheck);
            pstmtCheck.setInt(1, userId);
            ResultSet rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                // Affichage des informations actuelles
                System.out.println("Utilisateur trouvé : ");
                System.out.println("Prénom actuel : " + rs.getString("prenom"));
                System.out.println("Nom actuel : " + rs.getString("nom"));
                System.out.println("Email actuel : " + rs.getString("email"));

                // Demande des nouvelles valeurs
                System.out.print("Nouveau prénom (laisser vide pour conserver l'ancien) : ");
                String newPrenom = sc.nextLine();
                if (newPrenom.isEmpty()) {
                    newPrenom = rs.getString("prenom");
                }

                System.out.print("Nouveau nom (laisser vide pour conserver l'ancien) : ");
                String newNom = sc.nextLine();
                if (newNom.isEmpty()) {
                    newNom = rs.getString("nom");
                }

                System.out.print("Nouvel email (laisser vide pour conserver l'ancien) : ");
                String newEmail = sc.nextLine();
                if (newEmail.isEmpty()) {
                    newEmail = rs.getString("email");
                }

                // Mise à jour de l'utilisateur
                String sqlUpdate = "UPDATE utilisateurs SET prenom = ?, nom = ?, email = ? WHERE id = ?";
                PreparedStatement pstmtUpdate = connect.connexion.prepareStatement(sqlUpdate);
                pstmtUpdate.setString(1, newPrenom);
                pstmtUpdate.setString(2, newNom);
                pstmtUpdate.setString(3, newEmail);
                pstmtUpdate.setInt(4, userId);

                int rowsAffected = pstmtUpdate.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Mise à jour réussie !");
                } else {
                    System.out.println("Échec de la mise à jour.");
                }

                // Fermeture des ressources
                pstmtUpdate.close();
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }

    // Méthode de recherche de l'utilisateur par ID
    public void search(Connexion connect, Scanner sc) {
        System.out.println("Entrez l'ID de l'utilisateur à rechercher :");
        int userId = sc.nextInt();

        String sqlSelect = "SELECT id, prenom, nom, email FROM utilisateurs WHERE id = ?";
        try (PreparedStatement pstmt = connect.connexion.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Affichage des informations de l'utilisateur
                System.out.println("Utilisateur trouvé :");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Prénom : " + rs.getString("prenom"));
                System.out.println("Nom : " + rs.getString("nom"));
                System.out.println("Email : " + rs.getString("email"));
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }
}
