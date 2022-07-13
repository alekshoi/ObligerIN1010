class HvitRute extends Rute {
    HvitRute(int radNummer, int kolonneNummer, Labyrint tilhorighetLabyrint) {
        super(radNummer, kolonneNummer, tilhorighetLabyrint);
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public void finn(Rute fra) {
        super.finn(fra);
    }
}
