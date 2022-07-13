class Rute {
    private int plasseringRad, plaseringKolonne;

    Rute(int plasseringRad, int plaseringKolonne) {
        this.plasseringRad = plasseringRad;
        this.plaseringKolonne = plaseringKolonne;
    }

    public void endreRad(int rad) {
        plasseringRad = rad;
    }

    public void endreKolonne(int kolonne) {
        plaseringKolonne = kolonne;
    }

    public int hentRad() {
        return plasseringRad;
    }

    public int hentKolonne() {
        return plaseringKolonne;
    }
}
