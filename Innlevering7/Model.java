class Model {
    private View gui;
    private Koe<Slange> slangen = new Koe<>();

    private int antallSkatter = 10;
    private Skatt[] skattene = new Skatt[antallSkatter];
    private boolean kjorer = false;

    Model(View gui) {
        this.gui = gui;
    }

    public void startSpill() {
        kjorer = true;
        gui.startBrett();

    }

    boolean kjorerSpilletBool() {
        return kjorer;
    }

    public void avslutt(String melding) {
        // tar inn en feilmelding som gjør at vi kan bruke denne flere steder, og at 
        // den er mer spesifikk
        System.out.println(String.format("Spillet er ferdig: %s", melding));
        System.exit(0);
    }

    public boolean spiseSkatt() {
        // går gjennom hver skatt og sjekker om den ikke er spist, dvs. =null
        // hvis den ikke er spist sjekker man om første elementet i slangekoen er på
        // plassen
        // dens
        Slange hodet = slangen.hent();
        for (int i = 0; i < skattene.length; i++) {
            if (skattene[i] != null) {
                if (hodet.hentRad() == skattene[i].hentRad() && hodet.hentKolonne() == skattene[i].hentKolonne()) {
                    spisSkatt(i, hodet);
                    skattene[i] = null;
                    return true;
                }

            }
        }
        return false;
    }
    // bevegelsen vil gå ut på å legge et nytt slangeelement forrerst og fjerne det siste elementet i slangelisten
    // en annen, men mer avansert, måte å gjøre det på er å flytte hodet i riktig retning
    // deretter skal man flytte én og én slangedel, der hver del tar over plassen til den foran seg
    void beveg(String retning) {
        if(skatterIgjen()==0){
            avslutt("Du har spist opp alle skattene. Du vant! GRATULERER!");
        }
        int endringKolonne = 0;
        int endringRad = 0;
        // starter alltid med å avslutte hvis slangen treffer kanten
        // videre skal hodet gå i det nye retningen, også skal neste fylle opp
        // koordinatet til den foran seg.

        // if-sjekkene sjekker hva som er neste veien å gå og om man går utenfor rutenettet
        if (retning.equals("opp")) {
            if (slangen.hent(0).hentRad() == 0) {
                kjorer = false;
                avslutt("Du gikk opp i taket");
            }
            endringRad = -1;
        }
        if (retning.equals("ned")) {
            if (slangen.hent(0).hentRad() == gui.hentAntallKolonner() - 1) {
                kjorer = false;
                avslutt("Du gikk inn i bunnen av banen");
            }
            endringRad = 1;

        }
        if (retning.equals("hoyre")) {
            if (slangen.hent(0).hentKolonne() == gui.hentAntallKolonner() - 1) {
                kjorer = false;
                avslutt("Du gikk inn i hoyre vegg");
            }
            endringKolonne = 1;
            
        }
        if (retning.equals("venstre")) {
            if (slangen.hent(0).hentKolonne() == 0) {
                kjorer = false;
                avslutt("Du gikk inn venstre vegg");
            }
            endringKolonne = -1;
            
        }

        // legger til en ny slangedel foran det eksisterende hodet hvis den spiser.
        // hvis den ike spiser flytter man bare det eksisterende hodet ved å legge et nytt foran og fjerner siste element i køen
        Slange nyDel = new Slange(hentHode().hentRad()+endringRad, hentHode().hentKolonne()+endringKolonne, hentHode().hentRetning());
        if(!spiseSkatt()){
            slangen.fjern();
        }
        slangen.leggTil(nyDel);
    }
    public void trefferHale() {
        // henter ut hodet og sjekker om den har samme koordinat som en av de andre
        // delene.
        if(slangen.stoerrelse()==1){
            return;
        }
        for(int i=0; i<slangen.stoerrelse(); i++){
            if(i!=0){
                if (
                hentHode().hentKolonne() == slangen.hent(i).hentKolonne() &&
                hentHode().hentRad() == slangen.hent(i).hentRad()) 
                {
                    // hvis man krysser halen sin avslutter vi spillet med en feilmelding
                    avslutt("Du gikk inn i halen din");
                }
            }
        }
    }

    // lager noen hjelpemetoder
    private Slange hentHode() {
        return slangen.hent();
    }

    public Koe<Slange> hentSlange() {
        return slangen;
    }
    public Skatt[] hentSkatter() {
        return skattene;
    }

    public void leggTilSkatt(Skatt skatt) {
        for (int i = 0; i < antallSkatter; i++) {
            if (skattene[i] == null) {
                skattene[i] = skatt;
                return;
            }
        }
    }

    public void spisSkatt(int pos, Slange hode) {
        skattene[pos] = null;
    }
    private int skatterIgjen(){
        int antall = 0;
        for(Skatt skatt: skattene){
            if(skatt!=null){
                antall++;
            }
        }
        return antall;
    }
}
