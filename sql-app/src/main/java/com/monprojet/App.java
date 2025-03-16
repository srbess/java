package com.monprojet;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans votre application de gestion d'utilisateurs JAVA !");


        Connexion connexion = new Connexion();
        int choix = 0;
        Scanner sc = new Scanner(System.in);
        GestionUtilisateur gu = new GestionUtilisateur();


        try {
            do {
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1 - Ajouter un utilisateur");
                System.out.println("2 - Supprimer un utilisateur avec son ID");
                System.out.println("3 - Lister les utilisateurs");
                System.out.println("4 - Modifier un utilisateur");
                System.out.println("5 - Rechercher un utilisateur"); // Ajout de l'option de recherche
                System.out.println("0 - quitter");
                choix = sc.nextInt();

                switch (choix) {
                    case 1:
                        gu.add(connexion, sc);
                        break;
                    case 2:
                        gu.delete(connexion, sc);
                        break;
                    case 3:
                        gu.list(connexion);
                        break;
                    case 4:
                        gu.update(connexion, sc);
                        break;
                    case 5:
                        gu.search(connexion, sc);  // Option pour rechercher un utilisateur
                        break;
                    default:
                        System.out.println("L'action demandée n'existe pas !");
                        break;
                }
            } while (choix != 0);
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        } finally {
            try {
                connexion.close();
            } catch (Exception e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
            sc.close();
        }

        System.exit(0);
    }
}