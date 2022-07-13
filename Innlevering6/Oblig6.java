import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Oblig6 {
    public static void main(String[] args) throws FileNotFoundException {
        String filnavn = args[0];
        Labyrint labyrint = new Labyrint("labyrinter/" + filnavn);
        // Labyrint labyrint = new Labyrint("labyrinter/6.in");
        Scanner input = new Scanner(System.in);
        String s = "";
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
        s = input.nextLine();

        while (!s.equals("-1")) {
            String[] biter = s.split(" ");
            int rad = Integer.parseInt(biter[0]);
            int kolonne = Integer.parseInt(biter[1]);
            // finner åpning fra gitt koordinat
            ArrayList<Rute> aapninger = labyrint.finnUtveiFra(rad, kolonne);
            skrivUtAapninger(aapninger);
            System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
            s = input.nextLine();
        }
        input.close();
    }

    private static void skrivUtAapninger(ArrayList<Rute> liste) {
        System.out.println("Aapninger: ");
        if (liste == null) {
            System.out.println("Kan ikke starte i sort rute\n");
            return;
        }
        if (liste.isEmpty()) {
            System.out.println("Fant ingen aapninger fra oppgitt koordinat.");
        }
        for (Rute rute : liste) {
            System.out.println(String.format("(%s,%s)", rute.radNummer, rute.kolonneNummer));
        }

        System.out.println("");
    }

}
