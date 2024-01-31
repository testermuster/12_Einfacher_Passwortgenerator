import java.security.SecureRandom;
import java.util.Scanner;

public class EinfacherPasswortgenerator {

    private static final String GROSSBUCHSTABEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String KLEINBUCHSTABEN = "abcdefghijklmnopqrstuvwxyz";
    private static final String ZAHLEN = "0123456789";
    private static final String SONDERZEICHEN = "!@#$%&*";
    private static final String ALLE_ZEICHEN = GROSSBUCHSTABEN + KLEINBUCHSTABEN + ZAHLEN + SONDERZEICHEN;
    private static SecureRandom zufall = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Geben Sie die Länge des Passworts ein: ");
        int laenge = scanner.nextInt();
        if (laenge < 4) {
            System.out.println("Die Mindestlänge des Passworts sollte 4 Zeichen sein.");
        } else {
            System.out.println("Generiertes Passwort: " + generierePasswort(laenge));
        }

        scanner.close();
    }

    private static String generierePasswort(int laenge) {
        StringBuilder passwort = new StringBuilder(laenge);

        // Stelle sicher, dass das Passwort aus mindestens einem Zeichen jeder Kategorie besteht
        passwort.append(GROSSBUCHSTABEN.charAt(zufall.nextInt(GROSSBUCHSTABEN.length())));
        passwort.append(KLEINBUCHSTABEN.charAt(zufall.nextInt(KLEINBUCHSTABEN.length())));
        passwort.append(ZAHLEN.charAt(zufall.nextInt(ZAHLEN.length())));
        passwort.append(SONDERZEICHEN.charAt(zufall.nextInt(SONDERZEICHEN.length())));

        // Fülle den Rest des Passworts mit zufälligen Zeichen aus allen Kategorien
        for (int i = 4; i < laenge; i++) {
            passwort.append(ALLE_ZEICHEN.charAt(zufall.nextInt(ALLE_ZEICHEN.length())));
        }

        // Mische das Passwort, um die Sicherheit zu erhöhen
        return mischeZeichen(passwort.toString());
    }

    private static String mischeZeichen(String passwort) {
        char[] zeichen = passwort.toCharArray();
        for (int i = 0; i < zeichen.length; i++) {
            int index = zufall.nextInt(zeichen.length);
            char temp = zeichen[i];
            zeichen[i] = zeichen[index];
            zeichen[index] = temp;
        }
        return new String(zeichen);
    }
}
