
abstract class Resept {
    // gir unik ID til hver resept.
    private static int ID_teller;
    private int ID;

    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        ID_teller++;
        ID = ID_teller;
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        // gjør resepten ubrukulig hvis dette stemmer
        // kunne gjort en throw exception hvis dette hadde stemt, men fant ut at denne måten var lurest i og med at programmet ikke burde stoppe hvis det er tilfellet.
        // men nå gjør man fortsatt resepten ugyldig og programmet kan kjøre videre. 
        if ((legemiddel instanceof NarkotiskLegemiddel) && !(utskrivendeLege instanceof Spesialist)){
            this.reit = 0;
            System.out.println("Kan ikke opprette gi resept på Narkotisk legemiddel hvis legen ikke er spesialist.\n Setter reit til 0.");
        }

    }

    // enkle og selvforklarende metoder
    public int hentID() {
        return ID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    // bruker resept og returnerer om det er mulig eller ikke.
    public boolean bruk() {
        if (reit > 0) {
            reit -= 1;
            return true;
        }
        // System.out.println("Denne resepten er brukt opp.");
        return false;
    }

    // oppretter metoder som må spesifiserer subklassene
    abstract public String farge();

    abstract public int prisAaBetale();

    // overskriver toString metode og legger ved 
    public String toString() {
        String info = "\n\nLegemiddel: " + legemiddel.hentNavn();
        info += "\nIDResept: " + legemiddel.hentID();
        info += "\nUtskrivende lege: " + utskrivendeLege.hentNavn();
        info += "\nPasient: \n" + pasient;
        return info;
    }

}
