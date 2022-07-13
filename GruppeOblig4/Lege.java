public class Lege implements Comparable<Lege> {
    // tar inn navn 
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);

        if (legemiddel instanceof NarkotiskLegemiddel){
            throw new UlovligUtskrift(this, legemiddel);
        }
        utskrevneResepter.leggTil(nyResept);

        return nyResept;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        MilitaerResept nyResept = new MilitaerResept(legemiddel, this, pasient);

        if (legemiddel instanceof NarkotiskLegemiddel){
            throw new UlovligUtskrift(this, legemiddel);
        }
        utskrevneResepter.leggTil(nyResept);

        return nyResept;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        PResept nyResept = new PResept(legemiddel, this, pasient, reit);

        if (legemiddel instanceof NarkotiskLegemiddel){
            throw new UlovligUtskrift(this, legemiddel);
        }
        utskrevneResepter.leggTil(nyResept);

        return nyResept;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);

        if (legemiddel instanceof NarkotiskLegemiddel){
            throw new UlovligUtskrift(this, legemiddel);
        }
        utskrevneResepter.leggTil(nyResept);
        System.out.println("ja");

        return nyResept;
    }

    // henter info og returnerer en string
    @Override
    public String toString() {
        return "\nNavn: " + navn + "\n";
    }

    public int compareTo(Lege lege) {
        return this.navn.compareTo(lege.hentNavn());
    }

    public String hentKontrollID(){
        return "0";
    }
}
