
public class Pasient {
    String navn;
    String fodselnr;
    static int ID_teller = 0;
    int ID;
    IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public Pasient(String navn, String fodselnr) {
        this.navn = navn;
        this.fodselnr = fodselnr;
        ID_teller++;
        ID = ID_teller;
    }

    public int hentID() {
        return ID;
    }

    public String hentFodselnr() {
        return fodselnr;
    }

    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentResepter() {
        return reseptListe;
    }

    public void leggTilResept(Resept nyResept) {
        reseptListe.leggTil(nyResept);
    }

    public String toString() {
        String str = "\nNavn: " + navn;
        str += "\nFodselsnummer: " + fodselnr;
        str += "\nID: " + ID;

        return str;
    }

}
