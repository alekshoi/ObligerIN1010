public class Lege {
    // tar inn navn 
    protected String navn;

    Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    // henter info og returnerer en string
    @Override
    public String toString() {
        return "\n\nNavn: " + navn ;
    }
}
