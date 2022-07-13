
class Aapning extends HvitRute {

    Aapning(int radNummer, int kolonneNummer, Labyrint tilhorighetLabyrint) {
        super(radNummer, kolonneNummer, tilhorighetLabyrint);
    }

    @Override
    public void finn(Rute fra) {
        // legger til i liste hvis man har funnet en åpning
        tilhorighetLabyrint.aapninger.add(this);
    }

}
