class Spesialist extends Lege implements Godkjenningsfritak {
    String kontrollID;

    Spesialist(String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    // overskriver tom metode fra superklasse
    @Override
    public String hentKontrollID() {
        return kontrollID;
    }

    // henter info fra superklasse og legger til unik info
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nKontrollID: " + kontrollID;
        return info;
    }

}
