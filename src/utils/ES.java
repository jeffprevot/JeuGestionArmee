package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jeff
 */
public class ES {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Méthode de saisie d'un entier compris dans l'intervalle [inf, sup].
     * Affiche un message d'erreur si le type de la saisie ne correspond pas au type attendu.
     * @param message
     * @param inf
     * @param sup
     * @return Retourne la valeur saisie si elle est comprise dans l'intervalle [inf; sup]
     * @throws InputException est levée si l’entier saisi est en dehors de l’intervalle,
     *  et redonne la main à l'utilisateur après avoir proposé de quitter l'application.
     */
    public static int saisie(String message, int inf, int sup) throws InputException {
        int saisie;

        do {
            affiche("\t" + message + ": ");
            try {
                saisie = sc.nextInt();
                sc.nextLine();

                if (saisie >= inf && saisie <= sup) {
                    return saisie;
                }

                throw new InputException();

            } catch (InputMismatchException ex) {
                affiche("### ERREUR ### Votre saisie doit etre un entier\n");
                sc.nextLine();
            } catch (InputException eu) {
                
                affiche("### ERREUR ### Votre saisie doit etre comprise entre " + inf + " et " + sup + "\n");
                char choix = saisie("ABANDON (A)? , RESSAISIR (R)? ").charAt(0);
                if (choix=='A' || choix=='a') {
                    throw eu;
                }
            }
        } while (true);

    }

    /**
     * Méthode de saisie d'un entier supérieur à la valeur du paramètre inf.
     * Affiche un message d'erreur si le type de la saisie ne correspond pas au type attendu.
     * @param message
     * @param inf
     * @return Retourne la valeur saisie si elle est supérieure à inf.
     * @throws InputException est levée si l’entier saisi est en dehors de l’intervalle,
     *  et redonne la main à l'utilisateur après avoir proposé de quitter l'application.
     */
    public static int saisie(String message, int inf) throws InputException {
        int saisie;

        do {
            affiche("\t" + message + ": ");
            try {
                saisie = sc.nextInt();
                sc.nextLine();

                if (saisie >= inf) {
                    return saisie;
                }

                throw new InputException();

            } catch (InputMismatchException ex) {
                affiche("### ERREUR ### Votre saisie doit être un entier\n");
                sc.nextLine();
            } catch (InputException ex) {
                
                affiche("### ERREUR ### Votre saisie être supérieure à " + inf + "\n");
                char choix = saisie("ABANDON (A)? , RESSAISIR (R)? ").charAt(0);
                if (choix=='A') {
                    throw ex;
                }
            }
        } while (true);
    }
    
    /**
     * Méthode de saisie d'une chaîne de caractères.
     * @param message
     * @return Retourne la chaine de caractères saisie. Aucun contrôle n’est nécessaire.
     */
    public static String saisie(String message) {

        affiche("\t" + message + ": ");

        return sc.nextLine();
    }
    
    
    /**
     * Affiche simplement la chaîne de caractère donnée en paramètre
     * @param texte
     */
    public static void affiche(String texte) {
        System.out.print(texte);
    }

}
