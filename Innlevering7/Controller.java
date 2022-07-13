
class Controller {
    private View view;
    private Model model; 
    private String retning = "ned";     

    // kan skrive inn ønsket soveperiode for klokke
    private final int sovKlokke = 2000; 
    
    Controller(){
        view = new View(this);
        model = new Model(view);
    }

    void klokke() throws InterruptedException{
        while(model.kjorerSpilletBool()){
            Thread.sleep(sovKlokke);
            oppdater();
            // avlsutter spillet hvis ikke den ikke skal kjøre lenger
            if(!model.kjorerSpilletBool()) System.exit(1);
            view.oppdaterBrett();
        }
    }
    // gjennom hentModell har man tilgang til alle metodene til modellen. 
    public Model hentModell(){
        return model;
    }

    void oppdater(){
        // beveger slangen først og sjekker deretter om den treffer halen. 
        bevegSlange();
        model.trefferHale();
    }

    public void bevegSlange(){
        model.beveg(retning);
    }
    public void endreRetning(String retning){
        this.retning = retning;
    }
    public void avsluttSpill(){
        System.exit(0);
    }

}
