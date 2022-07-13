
public class NarkotiskLegemiddel extends Legemiddel {
    // tar inn alle variabler som Legemiddel pluss styrke
    private int styrke;
    NarkotiskLegemiddel(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    int hentNarkotiskStyrke() {
        return styrke;
    }

    // tar inn info fra Legemiddel, og legger til styrke
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nStyrke: " + styrke;
        return info;
    }

}
