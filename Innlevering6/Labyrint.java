import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Labyrint {
    private int antallRader;
    private int antallKolonner;
    String filNavn;
    Rute[][] ruteReferanser;
    ArrayList<Rute> aapninger;

    Labyrint(String filnavn) throws FileNotFoundException {
        this.lesGjennomFil(filnavn);
    }

    public Rute[][] hentListe() {
        return ruteReferanser;
    }

    public int hentAntallRader() {
        return antallRader;
    }

    public int hentAntallKolonner() {
        return antallKolonner;
    }

    @Override
    public String toString() {
        String print = "";
        for (Rute[] rad : ruteReferanser) {
            for (Rute rute : rad) {
                print += rute;
            }
            print += "\n";
        }
        return print;
    }

    private void lesGjennomFil(String filnavn) throws FileNotFoundException {

        Scanner fil = null;
        File filen = new File(filnavn);
        try {
            fil = new Scanner(filen);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            throw exception;
        }
        String linje = fil.nextLine();
        String[] instansVariablerListe = linje.split(" ");

        int antallRaderIFil = Integer.parseInt(instansVariablerListe[0]);
        antallRader = antallRaderIFil;

        int antallKolonnerIFil = Integer.parseInt(instansVariablerListe[1]);
        antallKolonner = antallKolonnerIFil;

        Rute[][] listeSomReturneres = new Rute[antallRaderIFil][antallKolonnerIFil];

        int radNummer = 0;
        while (radNummer < antallRaderIFil) {
            int kolonneNummer = 0;
            linje = fil.nextLine();
            char[] karakterer = linje.toCharArray();
            Rute rute;
            while (kolonneNummer < antallKolonnerIFil) {
                if (karakterer[kolonneNummer] == '#') {
                    rute = new SvartRute(radNummer, kolonneNummer, this);
                } else {
                    rute = new HvitRute(radNummer, kolonneNummer, this);
                }
                listeSomReturneres[radNummer][kolonneNummer] = rute;
                kolonneNummer++;
            }
            radNummer++;
        }
        fil.close();
        ruteReferanser = listeSomReturneres;
        for (int i = 0; i < antallRaderIFil - 1; i++) {
            for (int j = 0; j < antallKolonnerIFil - 1; j++) {
                Rute rute = listeSomReturneres[i][j];
                if (rute instanceof HvitRute && (rute.ruteNord() == null || rute.ruteOest() == null
                        || rute.ruteSyd() == null || rute.ruteVest() == null)) {
                    listeSomReturneres[i][j] = new Aapning(rute.radNummer, rute.kolonneNummer, this);
                }
            }
        }
    }

    public ArrayList<Rute> hentAapninger() {
        return aapninger;
    }

    public ArrayList<Rute> finnUtveiFra(int rad, int kol) {
        aapninger = new ArrayList<>();
        Rute start = ruteReferanser[rad][kol];
        if (start instanceof SvartRute) {
            return null;
        }
        start.finn(null);

        return this.hentAapninger();
    }
}
