
public class VanedannendeLegemiddel extends Legemiddel {
    // tar inn styrke i tillegg til det som er for legemiddel
    int styrke;

    VanedannendeLegemiddel(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    int hentVanedannendeStyrke() {
        return styrke;
    }

    @Override
    int hentStyrke(){
        return styrke;
    }
    // henter info fra superklasse og legger til noe indo
    @Override
    public String toString() {
        String info = super.toString();
        info += "Styrke: " + styrke + "\n";
        return info;
    }
}
