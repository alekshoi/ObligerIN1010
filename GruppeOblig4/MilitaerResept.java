class MilitaerResept extends HvitResept {
    MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) { // gjør reit til 0 og dermed er resepten ubrukelig hvis den ikk er skrevet av en lege
        super(legemiddel, utskrivendeLege, pasient, 3);
        if ((legemiddel instanceof NarkotiskLegemiddel) && !(utskrivendeLege instanceof Spesialist)){
            this.reit = 0;
            System.out.println("Kan ikke opprette gi resept på Narkotisk legemiddel hvis legen ikke er spesialist.\n Setter reit til 0.");
        }
    }

    // overskriver metode i henhold til oppgavetekst
    @Override
    public int prisAaBetale() {
        return 0;
    }
    
    // henter info fra superklasse og legger til unik info
    @Override
    public String toString() {
        String info = super.toString();
        info += "\nPris aa betale: " + prisAaBetale() + " kr.";
        info += "\nType: millitaer";
        info += "\n---------------------------------";
        return info;
    }

}
