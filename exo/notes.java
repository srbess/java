import java.util.Arrays;
import java.util.Scanner;

public class Notes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] notes = new int[10];

        System.out.println("Entrez 10 notes :");
        for (int i = 0; i < notes.length; i++) {
            System.out.print("Note " + (i + 1) + " : ");
            notes[i] = scanner.nextInt();
        }

        // Tri des notes
        Arrays.sort(notes);

        // Calcul des statistiques
        double moy = calculMoy(notes);
        double mediane = calculMediane(notes);
        double ecartType = calculEcartType(notes, moy);
        int min = notes[0];
        int max = notes[notes.length - 1];

        // Affichage des résultats
        System.out.println("\nTableau trié : " + Arrays.toString(notes));
        System.out.println("Moyenne : " + moy);
        System.out.println("Médiane : " + mediane);
        System.out.println("Écart-type : " + ecartType);
        System.out.println("Valeur minimale : " + min);
        System.out.println("Valeur maximale : " + max);

        scanner.close();
    }

    // Méthode pour calculer la moyenne des notes
    public static double calculMoy(int[] tableau) {
        double somme = 0;
        for (int note : tableau) {
            somme += note;
        }
        return somme / tableau.length;
    }

    // Méthode pour calculer la médiane des notes
    public static double calculMediane(int[] tableau) {
        int n = tableau.length;
        if (n % 2 == 0) {
            return (tableau[n / 2 - 1] + tableau[n / 2]) / 2.0;
        } else {
            return tableau[n / 2];
        }
    }

    // Méthode pour calculer l'écart-type des notes
    public static double calculEcartType(int[] tableau, double moy) {
        double somme = 0;
        for (int note : tableau) {
            somme += Math.pow(note - moy, 2);
        }
        return Math.sqrt(somme / tableau.length);
    }
}
