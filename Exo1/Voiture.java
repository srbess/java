package Exo1;

import java.util.Scanner;

public class Voiture {

    public String[] couleurAuto={"Rouge", "Vert", "Gris"};
    public String marque; 
    public String modele;
    public String couleur; 
    
    public  void demarrer() {
        System.out.println( "La voiture à démarrer !");   
    }
    public  void freiner() {
        System.out.println( "La voiture à freiner !");   
    }

    public  void accelerer() {
        System.out.println( "La voiture à accélerer !");   
    }

    public boolean estCouleurValide(String couleur) {
        for (String c : couleurAuto) {
            if (c.equalsIgnoreCase(couleur)) {
                return true;
            }
        }
        return false;
    }
    
}
