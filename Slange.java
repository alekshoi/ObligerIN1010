class Slange extends Rute {
    //private boolean hode;
    private String retning;

    Slange(int plasseringRad, int plaseringKolonne, String retning) {
        super(plasseringRad, plaseringKolonne);
        this.retning = retning;
        //this.hode = hode;

    }
    public void endreRetning(String nyRetning){
        this.retning = nyRetning;
    }
    public String hentRetning(){
        return retning;
    }
    /*void gjorTilHode(){
        hode = true;
    } 
    void gjorTilKropp(){
        hode = false;
    }

    public boolean erHode() {
        return hode;
    }*/


}
