class SvartRute extends Rute {
    SvartRute(int radNummer, int kolonneNummer, Labyrint tilhorighetLabyrint) {
        super(radNummer, kolonneNummer, tilhorighetLabyrint);
    }

    @Override
    public String toString() {
        return "#";
    }

    @Override
    public void finn(Rute fra) {
        // man skal ikke sjekke videre hvis man kommer til en svart rute. Da er ikke
        // veien gyldig.
        return;
    }
}
