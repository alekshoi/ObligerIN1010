class Hovedprogram {
    public static void main(String[] args) throws Exception {
        // oppretter nødvendige variabler for å teste og å skrive ut.
        VanligLegemiddel ibux = new VanligLegemiddel("Ibux", 150, 10.4);
        VanedannendeLegemiddel ritalin = new VanedannendeLegemiddel("Ritalin", 249, 15.6, 10);
        NarkotiskLegemiddel cannabis = new NarkotiskLegemiddel("Cannabis", 500, 55.4, 15);
        VanligLegemiddel pPiller = new VanligLegemiddel("P-piller", 70, 8.5);

        Lege erik = new Lege("Erik");
        Spesialist ida = new Spesialist("Ida", "156");

        PResept reseptPPiller = new PResept(pPiller, erik, 160, 10);
        HvitResept ibuxResept = new HvitResept(ibux, erik, 154, 3);
        BlaaResept ritalinResept = new BlaaResept(ritalin, ida, 54, 2);
        MilitaerResept cannabisResept = new MilitaerResept(cannabis, erik, 23);

        // skriver ut alle variablene
        System.out.println("\n --INFO--\n");
        System.out.println("\n-Info legemidler-" + ibux + ritalin + cannabis + pPiller);
        System.out.println("\n-Info leger-" + erik + ida);
        System.out.println("\n-Info resepter-\n" + reseptPPiller + ibuxResept + ritalinResept + cannabisResept);

        System.out.println("\n-Kjorer tester-\n");
        if (ritalin.hentVanedannendeStyrke() == 10 
        && erik.hentNavn() == "Erik" 
        && reseptPPiller.prisAaBetale() == 0
        && cannabisResept.bruk() == false
        && cannabisResept.hentReit() == 0){
            System.out.println("alt stemmer");
        }
        else{
            System.out.println("Noe er feil");
        }
        

    }
}
