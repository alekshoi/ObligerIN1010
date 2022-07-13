public class PResept extends HvitResept {
    PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    // skriver over metode fra superklasse
    @Override
    public int prisAaBetale() {
        if (legemiddel.hentPris() - 180 >= 0) {
            return legemiddel.hentPris() - 180;
        }
        return 0;
    }

    // henter info fra superklasse og legger til litt mer info
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nReit: " + reit;
        info += "\nPris aa betale: " + prisAaBetale() + " kr. ";
        return info;
    }
}
