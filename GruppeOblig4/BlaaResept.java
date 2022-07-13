public class BlaaResept extends Resept {
    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // overskriver tomme metoder fra superklasser i henhold til oppgavetekst
    @Override
    public String farge() {
        return "blaa";
    }
    @Override
    public int prisAaBetale() {
        return  (int) (Math.round(legemiddel.hentPris())*0.25);
    }

    // henter info fra superklasse og legger til unik info
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nReit: " + reit;
        info += "\nFarge: " + farge();
        info += "\nPris aa betale: " + prisAaBetale() + " kr.";
        info += "\n---------------------------------";
        return info;
    }
}
