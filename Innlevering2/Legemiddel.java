
abstract class Legemiddel {
    // gir unik id
    protected String navn;
    protected static int ID_teller;
    // resten av instansvariabler
    protected int ID;
    protected int pris;
    protected double virkestoff;

    Legemiddel(String navn, int pris, double virkestoff) {
        ID_teller++;
        ID = ID_teller;
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
    }

    // selvforklarende metoder
    int hentID() {
        return ID;
    }

    String hentNavn() {
        return navn;
    }

    int hentPris() {
        return pris;
    }

    double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nypris) {
        pris = nypris;
    }

    // gjør at man senere kan printe legemidlene. 
    @Override
    public String toString() {
        String info = "\n\nNavn: " + navn;
        info += "\nID: " + (int) ID;
        info += "\nPris:  " + pris + " kr";
        info += "\nVirkestoff: " + virkestoff;
        return info;
    }
}