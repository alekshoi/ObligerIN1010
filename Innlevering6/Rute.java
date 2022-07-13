abstract class Rute {
    protected int radNummer;
    protected int kolonneNummer;
    protected Labyrint tilhorighetLabyrint;

    Rute(int radNummer, int kolonneNummer, Labyrint tilhorighetLabyrint) {
        this.radNummer = radNummer;
        this.kolonneNummer = kolonneNummer;
        this.tilhorighetLabyrint = tilhorighetLabyrint;
    }

    public Rute ruteNord() {
        Rute[][] liste = tilhorighetLabyrint.hentListe();
        if (radNummer == 0) {
            return null;
        }
        return liste[radNummer - 1][kolonneNummer];
    }

    public Rute ruteSyd() {
        Rute[][] liste = tilhorighetLabyrint.hentListe();
        if (radNummer == tilhorighetLabyrint.hentAntallRader() - 1) {
            return null;
        }
        return liste[radNummer + 1][kolonneNummer];
    }

    public Rute ruteOest() {
        Rute[][] liste = tilhorighetLabyrint.hentListe();
        if (kolonneNummer == tilhorighetLabyrint.hentAntallKolonner() - 1) {
            return null;
        }
        return liste[radNummer][kolonneNummer + 1];
    }

    public Rute ruteVest() {
        Rute[][] liste = tilhorighetLabyrint.hentListe();
        if (kolonneNummer == 0) {
            return null;
        }
        return liste[radNummer][kolonneNummer - 1];
    }

    public void finn(Rute fra) {
        if (this.ruteNord() != fra) {
            this.ruteNord().finn(this);
        }
        if (this.ruteOest() != fra) {
            this.ruteOest().finn(this);
        }
        if (this.ruteVest() != fra) {
            this.ruteVest().finn(this);
        }
        if (this.ruteSyd() != fra) {
            this.ruteSyd().finn(this);
        }
    }

}
