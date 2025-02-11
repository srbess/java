public class   rebours{

    public static void main(String[] args) {
        System.out.println("Lancement");
        
        for (int i = 10; i >= 1; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


import java.util.Arrays;
import java.util.Scanner;

public class StatistiquesTableau{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nombres = new int[10];

       
        System.out.println("Entrez 10 nombres entiers :");
        for (int i = 0; i < nombres.length; i++) {
            System.out.print("Nombre " + (i + 1) + " : ");
            nombres[i] = scanner.nextInt();
        }

      
        Arrays.sort(nombres);

        double moyenne = calculerMoyenne(nombres);
        double mediane = calculerMediane(nombres);
        double ecartType = calculerEcartType(nombres, moyenne);
        int min = nombres[0];
        int max = nombres[nombres.length - 1];

     
        System.out.println("\nTableau trié : " + Arrays.toString(nombres));
        System.out.println("Moyenne : " + moyenne);
        System.out.println("Médiane : " + mediane);
        System.out.println("Écart-type : " + ecartType);
        System.out.println("Valeur minimale : " + min);
        System.out.println("Valeur maximale : " + max);

        scanner.close();
    }

    

