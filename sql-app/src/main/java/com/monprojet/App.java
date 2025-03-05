package com.monprojet;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mabasegr1";
        String utilisateur = "root";
        String motDePasse = "";
        Connection connexion = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Vous êtes connectez !");

            System.out.println("Que voulez-vous faire ?");
            System.out.println("1 - Ajouter un utilisateur");
            System.out.println("2 - Supprimer un utilisateur");
            System.out.println("3 - Lister les utilisateurs");
            System.out.println("4 - Editer un utilisateur");
            System.out.println("5 - Rechercher un utilisateur");
            System.out.println("6 - Quitter");
            System.out.print("Choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                System.out.print("Entrez le nom : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez l'email : ");
                String email = scanner.nextLine();

                String sqlInsert = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
                PreparedStatement pstmt = connexion.prepareStatement(sqlInsert);
                pstmt.setString(1, nom);
                pstmt.setString(2, email);
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Utilisateur a été ajouté !");
                }
                pstmt.close();
            } else if (choix == 2) {
                System.out.print("Entrez l'ID de l'utilisateur que vous voulez supprimer : ");
                int idSuppr = scanner.nextInt();

                String sqlDelete = "DELETE FROM utilisateurs WHERE id = ?";
                PreparedStatement pstmt = connexion.prepareStatement(sqlDelete);
                pstmt.setInt(1, idSuppr);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Utilisateur supprimé !");
                } else {
                    System.out.println("Aucun utilisateur ne correspond à cet ID.");
                }
                pstmt.close();
            } else if (choix == 3) {
                Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT id, nom, email FROM utilisateurs");

                System.out.println("\nListe des utilisateurs :");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("nom");
                    String email = rs.getString("email");
                    System.out.println("ID : " + id + ", Nom : " + nom + ", Email : " + email);
                }

                rs.close();
                stmt.close();
            } else if (choix == 4) {
                System.out.print("Entrez l'ID de l'utilisateur à éditer : ");
                int idEdit = scanner.nextInt();
                scanner.nextLine();

                String sqlSelect = "SELECT id, nom, email FROM utilisateurs WHERE id = ?";
                PreparedStatement pstmtSelect = connexion.prepareStatement(sqlSelect);
                pstmtSelect.setInt(1, idEdit);
                ResultSet rs = pstmtSelect.executeQuery();

                if (rs.next()) {
                    String nomActuel = rs.getString("nom");
                    String emailActuel = rs.getString("email");

                    System.out.println("Utilisateur trouvé :");
                    System.out.println("Nom actuel : " + nomActuel);
                    System.out.println("Email actuel : " + emailActuel);

                    System.out.print("Entrez le nouveau nom (laisser vide pour ne pas modifier) : ");
                    String nouveauNom = scanner.nextLine();
                    if (nouveauNom.isEmpty()) {
                        nouveauNom = nomActuel;
                    }

                    System.out.print("Entrez le nouveau email (laisser vide pour ne pas modifier) : ");
                    String nouvelEmail = scanner.nextLine();
                    if (nouvelEmail.isEmpty()) {
                        nouvelEmail = emailActuel;
                    }

                    String sqlUpdate = "UPDATE utilisateurs SET nom = ?, email = ? WHERE id = ?";
                    PreparedStatement pstmtUpdate = connexion.prepareStatement(sqlUpdate);
                    pstmtUpdate.setString(1, nouveauNom);
                    pstmtUpdate.setString(2, nouvelEmail);
                    pstmtUpdate.setInt(3, idEdit);

                    int rowsUpdated = pstmtUpdate.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Utilisateur modifié !");
                    } else {
                        System.out.println("Erreur lors de la modification.");
                    }

                    pstmtUpdate.close();
                } else {
                    System.out.println("Aucun utilisateur ne correspond à cet ID. " + idEdit);
                }

                rs.close();
                pstmtSelect.close();
            } else if (choix == 5) {
                System.out.println("Voulez-vous rechercher par :");
                System.out.println("1 - Nom");
                System.out.println("2 - Email");
                System.out.print("Choix : ");
                int critereRecherche = scanner.nextInt();
                scanner.nextLine();

                if (critereRecherche == 1) {
                    System.out.print("Entrez le nom de l'utilisateur à rechercher : ");
                    String nomRecherche = scanner.nextLine();
                    String sqlRechercheNom = "SELECT id, nom, email FROM utilisateurs WHERE nom LIKE ?";
                    PreparedStatement pstmtRechercheNom = connexion.prepareStatement(sqlRechercheNom);
                    pstmtRechercheNom.setString(1, "%" + nomRecherche + "%");
                    ResultSet rsNom = pstmtRechercheNom.executeQuery();

                    while (rsNom.next()) {
                        System.out.println("ID : " + rsNom.getInt("id") + ", Nom : " + rsNom.getString("nom") + ", Email : " + rsNom.getString("email"));
                    }

                    rsNom.close();
                    pstmtRechercheNom.close();
                }
            } else if (choix == 6) {
                System.out.println("Fin !");
                return;
            } else {
                System.out.println("Choix invalide !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        } finally {
            if (connexion != null) {
                try {
                    connexion.close();
                    System.out.println("Déconnexion avec succès.");
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la déconnexion : " + e.getMessage());
                }
            }
            scanner.close();
        }
    }
}
