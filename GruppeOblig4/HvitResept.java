public class HvitResept extends Resept {
    HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // overskriver tomme metoder fra superklasser i henhold til oppgavetekst
    @Override
    public String farge() {
        return "hvit";
    }
    
    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    // henter info fra superklasse og legger til unik info
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nReit: " + reit;
        info += "\nFarge: " + farge();
        if (!(this instanceof MilitaerResept) && !(this instanceof PResept)) {
            info += "\n---------------------------------";
        }
        return info;
    }
}
