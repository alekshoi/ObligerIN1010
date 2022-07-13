import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;


public class Legesystem {

    // oppretter indekserte lister for å ta vare på objektene
    IndeksertListe<Pasient> pasientListe = new IndeksertListe<>();
    IndeksertListe<Legemiddel> lmListe = new IndeksertListe<>();
    IndeksertListe<Lege> legeListe = new IndeksertListe<>(); // lager først en indeksert liste fordi vi bruker hent-metodem
    IndeksertListe<Resept> reseptListe = new IndeksertListe<>();

    public Legesystem(String filnavn) throws FileNotFoundException, NumberFormatException, UlovligUtskrift{
        lesFraFil(filnavn);
        kommandoLokke();
    }

    // leser inn fra fil
    private void lesFraFil(String filnavn) throws FileNotFoundException, NumberFormatException, UlovligUtskrift {
        Scanner fil = null;
        File filen = new File(filnavn);
        // sjekker om filen finnes
        try {
            fil = new Scanner(filen);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            System.exit(1);
        }

        String linje = "";
        int hashtagTeller = 0;

        // så lenge filen har flere linjer
        while (fil.hasNextLine()) {
            linje = fil.nextLine();
            //sjekker om linjen skal leses inn eller ikke
            if (linje.contains("#")) {
                hashtagTeller++;
                linje = fil.nextLine();
            }
            String[] biter = linje.split(","); // deler linjen opp i biter, ved komma
            if (hashtagTeller == 1) { // leser inn pasient
                leggTilPasient(biter);
            } else if (hashtagTeller == 2) { // leser inn legemiddel
                leggTilLegemiddel(biter);
            } else if (hashtagTeller == 3) { //leser inn lege
                leggTilLege(biter);
            } else { // leser inn resept
                leggTilResept(biter);
            }
        }
    }
    
    private void leggTilPasient(String[] biter) {
        Pasient pasient = new Pasient(biter[0], biter[1]);
        pasientListe.leggTil(pasient);
    }

    private void leggTilLegemiddel(String[] biter) {
        Legemiddel lm;
                if (biter[1].equals("narkotisk")) { // sjekker om legemiddelet er narkotisk
                    lm = new NarkotiskLegemiddel(biter[0], Integer.parseInt(biter[2]), Double.parseDouble(biter[3]),
                            Integer.parseInt(biter[4]));
                } else if (biter[1].equals("vanedannende")) { //sjekker om legemiddelet er vanedannende
                    lm = new VanedannendeLegemiddel(biter[0], Integer.parseInt(biter[2]), Double.parseDouble(biter[3]),
                            Integer.parseInt(biter[4]));
                } else { // hvis ikke det er narkotisk eller vanedannende, er legemiddelet vanlig
                    lm = new VanligLegemiddel(biter[0], Integer.parseInt(biter[2]), Double.parseDouble(biter[3]));
                }
                lmListe.leggTil(lm);
    }

    private void leggTilLege(String[] biter) {
        Lege lege;
        if (biter[1].equals("0")) { // sjekker om legen er en standard lege
            lege = new Lege(biter[0]);
        } else { // hvis ikke, er legen er spesialist
            lege = new Spesialist(biter[0], biter[1]);
        }
        legeListe.leggTil(lege);
    }

    private void leggTilResept(String[] biter) throws NumberFormatException, UlovligUtskrift {
        Legemiddel legemiddel = null;
        Lege utskrivendeLege = null;
        Pasient pasient = null;
        for (int i = 0; i < lmListe.stoerrelse(); i++) { // går gjennom listen med legemidler
            if (Integer.parseInt(biter[0]) == lmListe.hent(i).hentID()) { // sjekker om IDen er lik den fra filen
                legemiddel = lmListe.hent(i);
            }
        }
        for (int i = 0; i < legeListe.stoerrelse(); i++) { // går gjennom listen med leger
            if (biter[1].equals(legeListe.hent(i).hentNavn())) { // sjekker om navnet er likt det fra filen
                utskrivendeLege = legeListe.hent(i);
            }
        }
        for (int i = 0; i < pasientListe.stoerrelse(); i++) { // går gjennom listen med pasienter
            if (Integer.parseInt(biter[2])== pasientListe.hent(i).hentID()){ // sjekker om IDen er lik den fra filen
                pasient = pasientListe.hent(i);
            }
        }

        Resept res1;
        if (biter[3].equals("hvit")) { // sjekker om resepten skal være hvit
            res1 = utskrivendeLege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(biter[4]));
            reseptListe.leggTil(res1);
        }
        else if (biter[3].equals("blaa")) { // sjekker om resepten skal være blaa
            res1 = utskrivendeLege.skrivBlaaResept(legemiddel, pasient, Integer.parseInt(biter[4]));
            reseptListe.leggTil(res1);
        }
        else if (biter[3].equals("militaer")) { // sjekker om resepten skal være militaer
            res1 = utskrivendeLege.skrivMilitaerResept(legemiddel, pasient);
            reseptListe.leggTil(res1);
        }
        else { // resepten skal være PResept
            res1 = utskrivendeLege.skrivPResept(legemiddel, pasient, Integer.parseInt(biter[4]));
            reseptListe.leggTil(res1);
        }
        pasient.leggTilResept(res1);
    }

    // henter brukerens input, og sender oss videre til riktig metode
    public void input() throws UlovligUtskrift{ 
        Scanner sc = new Scanner(System.in);
        Integer kommando = null;
        try {
            kommando = Integer.parseInt(sc.nextLine()); 
        }
        catch (NumberFormatException e) {
            System.out.println("Ugyldig input, prov igjen.");
            kommandoLokke();
        }
        if (kommando > 0 && kommando < 7){
            if (kommando == 1){
                skrivUtAlt();
            }
            else if (kommando == 2){
                leggTilObjekt(sc);
            }
            else if (kommando == 3){
                brukResept(sc);
            }
            else if (kommando == 4){
                skrivUtStatistikk(sc);
            }
            else if (kommando == 5){
                skrivTilFil();
            }
            else if (kommando == 6){
                sc.close();
                avslutt();
            }
        }
        else{          
            System.out.println("Ugyldig input, prov igjen.");
            kommandoLokke();          
        }
     }

     // hovedmeny
    public void kommandoLokke() throws UlovligUtskrift{
         String string = "\n ----Kommandolokke----";
         string += "\n Skriv ut fullstending oversikt over pasienter, leger, legemidler og resepter (1)";
         string += "\n Opprette og legge til nye elementer i systemet(2)";
         string += "\n Bruke en gitt resept fra listen til en pasient(3)";
         string += "\n Skrive ut forskjellige former for statistikk(4)";
         string += "\n SKrive ut alle data til fil(5)";
         string += "\n Avslutt(6)";
         System.out.println(string);
         input();
     }

     // skriver ut all informasjon
    private void skrivUtAlt() {
        Prioritetskoe<Lege> nyListe = new Prioritetskoe<>(); // gjør om til prioritetskø for å sortere alfabetisk
        //kun legene
        for (int i = 0; i < legeListe.stoerrelse(); i++) {
            nyListe.leggTil(legeListe.hent(i));
        }

        System.out.println("----------LEGER----------");
        System.out.println(nyListe);
        System.out.println("\n---------PASIENTER---------");
        System.out.println(pasientListe);
        System.out.println("\n---------LEGEMIDLER---------");
        System.out.println(lmListe);
        System.out.println("----------RESEPTER----------");        
        System.out.println(reseptListe);
     }

     // legger til objekter
    public void leggTilObjekt(Scanner sc) throws UlovligUtskrift{
        
        System.out.println("Tast 1 for lege");
        System.out.println("\nTast 2 for Legemiddel");
        System.out.println("\nTast 3 for pasient");
        System.out.println("\nTast 4 for resept");
        int tast = sc.nextInt();
        System.out.println(tast);
        
        if (tast == 1){
            leggTilLege(sc);
        }
        else if (tast == 2){
            leggTilLegemiddel(sc);
        }
        else if (tast == 3){
            leggTilPasient(sc);
        }
        else if (tast == 4){
            leggTilResept(sc);
        }
     }

     private void leggTilLege(Scanner sc) throws UlovligUtskrift {
        System.out.println("Navnet paa legen?");
            sc.nextLine();
            String navn = sc.nextLine();
            System.out.println("Trykk 1 for vanlig lege, 2 for spesialist");
            Integer kommando = sc.nextInt();

            if (kommando == 1){
                Lege lege = new Lege(navn);
                legeListe.leggTil(lege);
                kommandoLokke();
            } else if (kommando == 2){
                System.out.println("KontrollID ?");  
                sc.nextLine();              
                String kontrollID = sc.nextLine();
                Spesialist spesialist = new Spesialist(navn, kontrollID);
                legeListe.leggTil(spesialist);
                kommandoLokke();
            }
     }
     
     private void leggTilLegemiddel(Scanner sc) throws UlovligUtskrift {
        System.out.println("\nTast 1 for Vanlig ");
        System.out.println("\nTast 2 for Narkotisk ");
        System.out.println("\nTast 3 for Vanedannende ");

        Integer legemiddelTast = sc.nextInt();
        if (legemiddelTast == 1){
            System.out.println("\nNavn?");
            //String scanNavn = sc.nextLine(); //her er det noe rart
            sc.nextLine();
            String sc2Navn = sc.nextLine();                
            System.out.println("\nPris?");
            Integer pris = sc.nextInt();
            System.out.println("Virkestoff?");
            double virkestoff = sc.nextDouble();
            VanligLegemiddel vl = new VanligLegemiddel(sc2Navn, pris, virkestoff);
            lmListe.leggTil(vl);
            kommandoLokke();
        }
        else if (legemiddelTast == 2) {
            System.out.println("\nNavn?");
            //String scanNavn = sc.nextLine(); //her er det noe rart
            sc.nextLine();
            String sc2Navn = sc.nextLine();                
            System.out.println("\nPris?");
            Integer pris = sc.nextInt();
            System.out.println("Virkestoff?");
            double virkestoff = sc.nextDouble();
            System.out.println("\nStyrke?");
            Integer styrke = sc.nextInt();
            NarkotiskLegemiddel nl = new NarkotiskLegemiddel(sc2Navn, pris, virkestoff, styrke);
            lmListe.leggTil(nl);
            kommandoLokke();
        }
        else if (legemiddelTast == 3) {
            System.out.println("\nNavn?");
            sc.nextLine(); // måtte til for å få det til å funke
            String sc2Navn = sc.nextLine();                
            System.out.println("\nPris?");
            Integer pris = sc.nextInt();
            System.out.println("Virkestoff?");
            double virkestoff = sc.nextDouble();
            System.out.println("\nStyrke?");
            Integer styrke = sc.nextInt();
            VanedannendeLegemiddel vdl = new VanedannendeLegemiddel(sc2Navn, pris, virkestoff, styrke);
            lmListe.leggTil(vdl);
            kommandoLokke();
        }
     }
     
     private void leggTilPasient(Scanner sc) throws UlovligUtskrift {
        System.out.print("\nNavn?\n");
        sc.nextLine();
        String navn = sc.nextLine();
        System.out.println("\nFodselsnummer?");            
        String fodselnr = sc.nextLine();
        Pasient pasient = new Pasient(navn, fodselnr);
        pasientListe.leggTil(pasient);
        kommandoLokke();
     }
     
     private void leggTilResept(Scanner sc) throws UlovligUtskrift {
        System.out.println("\n1 for p-resept\n2 for hvit resept\n3 for millitaer resept\n4 for blaa resept ");
        int kommando = sc.nextInt();
        Lege lege1 = null; Legemiddel lm1 = null; Pasient pasient1 = null;
        if (kommando == 1){
            leggTilPResept(sc, lege1, pasient1, lm1);
        } 
        else if (kommando == 2){
            leggTilHvitResept(sc, lege1, pasient1, lm1);
        }
        else if (kommando == 3){
            leggTilMilitaerResept(sc, lege1, pasient1, lm1);
        }
        else if (kommando == 4){
            leggTilBlaaResept(sc, lege1, pasient1, lm1);
        }
     }

     private void leggTilPResept(Scanner sc, Lege lege1, Pasient pasient1, Legemiddel lm1) throws UlovligUtskrift {
        System.out.println("\nLege?");
        sc.nextLine();
        String navn = sc.nextLine();
        boolean test = true;
        for (Lege lege : legeListe){
            if(navn.equals(lege.hentNavn())) {
                lege1 = lege;
                test = false;
            }
        }
        if (test) {
            System.out.println("Denne legen eksisterer ikke.");
            leggTilObjekt(sc);
        }
        System.out.println("\nPasient?");
        
        String pasient = sc.nextLine();
        for (Pasient pas : pasientListe){
            if (pasient.equals(pas.navn)){
                pasient1 = pas;
            }
        }
        System.out.println("\nLegemiddel?");
        String legemiddel = sc.nextLine();
        for (Legemiddel lm : lmListe){
            if (legemiddel.equals(lm.navn)){
                lm1 = lm;                     
            }
        }
        System.out.println("\nReit?");
        Integer reit = Integer.parseInt(sc.nextLine());
        PResept res = new PResept(lm1, lege1, pasient1, reit);
        reseptListe.leggTil(res);
        lege1.skrivPResept(lm1, pasient1, reit);
        pasient1.leggTilResept(res);
        kommandoLokke();
     }
     
     private void leggTilHvitResept(Scanner sc, Lege lege1, Pasient pasient1, Legemiddel lm1) throws UlovligUtskrift {
        System.out.println("\nLege?");
        sc.nextLine();
        String navn = sc.nextLine();
        boolean test = true;
        for (Lege lege : legeListe){
            if(navn.equals(lege.hentNavn())) {
                lege1 = lege;
                test = false;
            }
        }
        if (test) {
            System.out.println("Denne legen eksisterer ikke.");
            leggTilObjekt(sc);
        }
        System.out.println("\nPasient?");
        
        //sc.nextLine();
        String pasient = sc.nextLine();
        for (Pasient pas : pasientListe){
            if (pasient.equals(pas.navn)){
                pasient1 = pas;
            }
        }
        System.out.println("\nLegemiddel?");
        //sc.nextLine();
        String legemiddel = sc.nextLine();
        for (Legemiddel lm : lmListe){
            if (legemiddel.equals(lm.navn)){
                lm1 = lm;                      
            }
        }
        System.out.println("\nReit?");
        //sc.nextLine();
        Integer reit = Integer.parseInt(sc.nextLine());
        HvitResept hvit = new HvitResept(lm1, lege1, pasient1, reit);
        reseptListe.leggTil(hvit);
        lege1.skrivHvitResept(lm1, pasient1, reit);
        pasient1.leggTilResept(hvit);
        kommandoLokke();
     }
     
    private void leggTilMilitaerResept(Scanner sc, Lege lege1, Pasient pasient1, Legemiddel lm1) throws UlovligUtskrift {
        System.out.println("\nLege?");
            sc.nextLine();
            String navn = sc.nextLine();
            boolean test = true;
            for (Lege lege : legeListe){
                if(navn.equals(lege.hentNavn())) {
                    lege1 = lege;
                    test = false;
                }
            }
            if (test) {
                System.out.println("Denne legen eksisterer ikke.");
                leggTilObjekt(sc);
            }
            System.out.println("\nPasient?");
            
            //sc.nextLine();
            String pasient = sc.nextLine();
            for (Pasient pas : pasientListe){
                if (pasient.equals(pas.navn)){
                    pasient1 = pas;
                }
            }
            System.out.println("\nLegemiddel?");
            //sc.nextLine();
            String legemiddel = sc.nextLine();
            for (Legemiddel lm : lmListe){
                if (legemiddel.equals(lm.navn)){
                    lm1 = lm;                  
                }
            }
            MilitaerResept mill = new MilitaerResept(lm1, lege1, pasient1);
            lege1.skrivMilitaerResept(lm1, pasient1);
            reseptListe.leggTil(mill);
            pasient1.leggTilResept(mill);
            kommandoLokke();
    }
     
    private void leggTilBlaaResept(Scanner sc, Lege lege1, Pasient pasient1, Legemiddel lm1) throws UlovligUtskrift {
        System.out.println("\nLege?");
        sc.nextLine();
        String navn = sc.nextLine();
        boolean test = true;
        for (Lege lege : legeListe){
            if(navn.equals(lege.hentNavn())) {
                lege1 = lege;
                test = false;
            }
        }
        if (test) {
            System.out.println("Denne legen eksisterer ikke.");
            leggTilObjekt(sc);
        }
        System.out.println("\nPasient?");
        
        //sc.nextLine();
        String pasient = sc.nextLine();
        for (Pasient pas : pasientListe){
            if (pasient.equals(pas.navn)){
                pasient1 = pas;
            }
        }
        System.out.println("\nLegemiddel?");
        //sc.nextLine();
        String legemiddel = sc.nextLine();
        for (Legemiddel lm : lmListe){
            if (legemiddel.equals(lm.navn)){
                lm1 = lm;
            }
        }
        System.out.println("\nReit?");
        //sc.nextLine();
        Integer reit = Integer.parseInt(sc.nextLine());
        BlaaResept blaa = new BlaaResept(lm1, lege1, pasient1, reit);
        reseptListe.leggTil(blaa);
        lege1.skrivBlaaResept(lm1, pasient1, reit);
        pasient1.leggTilResept(blaa);
        kommandoLokke();
    }
     
     // avslutter programmet
    public void avslutt() {
        System.exit(1);
     }

    public void brukResept(Scanner sc) throws UlovligUtskrift {
        System.out.println("\nHvilken pasient vil du se resepter for?");
        for (int i = 0; i < pasientListe.stoerrelse(); i++) {
            System.out.println(i + ": " + pasientListe.hent(i).hentNavn() + "(fnr " + pasientListe.hent(i).hentFodselnr() + ")\n");
        }
        Integer input = null;
        try {
            input = Integer.parseInt(sc.nextLine()); 
        }
        catch (NumberFormatException e) {
            System.out.println("Ugyldig input, prov igjen.");
            brukResept(sc);
        }
        System.out.println("Valgt pasient: " + pasientListe.hent(input).hentNavn() + "(fnr " + pasientListe.hent(input).hentFodselnr() + ")\n");
        System.out.println("Hvilken resept vil du bruke?");
        int teller = 0;
        for (Resept resept : pasientListe.hent(input).hentResepter()) {
            System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + " reit)");
            teller++;
        }
        Integer input2 = null;
        try {
            input2 = Integer.parseInt(sc.nextLine()); 
        }
        catch (NumberFormatException e) {
            System.out.println("Ugyldig input, prov igjen.");
            brukResept(sc);
        }
        if (pasientListe.hent(input).hentResepter().hent(input2).bruk()) {
            System.out.println("Brukte resept paa " + pasientListe.hent(input).hentResepter().hent(input2).hentLegemiddel().hentNavn() + "Antall gjenvaerende reit: " + pasientListe.hent(input).hentResepter().hent(input2).hentReit());
            kommandoLokke();
        }
        else {
            System.out.println("Kan ikke bruke resept på " + pasientListe.hent(input).hentResepter().hent(input2).hentLegemiddel().hentNavn() + " (ingen gjenvaerende reit)");
            brukResept(sc);
        }
    }

    public void skrivUtStatistikk(Scanner sc) throws UlovligUtskrift {
        int antallVD = 0;
        int antallN = 0;
        String str = "\n";

        for (Resept resept : reseptListe) {
            if (resept.hentLegemiddel() instanceof VanedannendeLegemiddel) {
                antallVD++;
            }
            else if (resept.hentLegemiddel() instanceof NarkotiskLegemiddel){
                antallN++;
            }
        }

        if (antallVD > 0) {
            str += "Antall resepter paa vanedannende legemidler: " + antallVD;
        }
        if (antallN > 0) {
            str += "\nAntall resepter paa narkotisk legemidler: " + antallN;
        }

        str += "\n\nLeger som har skrevet ut en narkotisk resept:";

        for (Lege lege : legeListe) {
            int antall1 = 0;
            for (Resept resept : lege.hentUtskrevneResepter()) {
                if (resept.hentLegemiddel() instanceof NarkotiskLegemiddel) {
                    antall1++;
                }
            }
            if (antall1 > 0) {
                str += "\n" + lege.hentNavn() + ": " + antall1;
            }
        }

        str += "\n\nPasienter med narkotiske resepter:";

        for (Pasient pasient : pasientListe) {
            int antall2 = 0;
            for (Resept resept : pasient.hentResepter()) {
                if (resept.hentLegemiddel() instanceof NarkotiskLegemiddel) {
                    antall2++;
                }
            }
            if (antall2 > 0) {
                str += "\n" + pasient.hentNavn() + ": " + antall2;
            }
        }

        // for (Resept resept : reseptListe) {
        //     if (resept.hentLegemiddel() instanceof NarkotiskLegemiddel) {
        //         str += "\n" + resept.hentPasient().hentNavn() + ": " + 1;
        //     }
        // }

        System.out.println(str);
        kommandoLokke();
    }

    public void skrivTilFil() throws UlovligUtskrift {

        //oppretter nye filen
        File f = new File("testInnFil.txt");
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(f);
        } catch (Exception e ){
            System.out.println("feil");
            System.exit(1);
        }
        //starter med pasient
        pw.println("# Pasienter (navn, fnr)");
        for (Pasient pas : pasientListe){            
            pw.println(pas.navn + "," + pas.fodselnr);
        }
        //Legemidler
        pw.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel lm : lmListe){
            //sjekker type legemiddel
            //vanlig
            if (lm instanceof VanligLegemiddel){
                //konverterer virkemiddel fra double til int for utskriften
                int x = (int) lm.virkestoff;
                
                pw.println(lm.navn + ",vanlig," + lm.pris + "," + x );
            }
            //narkotisk 
            //måtte lage en abstrakt metode i legemiddel for å få tak i styrke
            else if (lm instanceof NarkotiskLegemiddel){            
                int x = (int) lm.virkestoff;
                pw.println(lm.navn + ",narkotisk," + lm.pris + "," + x + "," + lm.hentStyrke());
            }
            //vanedannende
            else{
                int x = (int) lm.virkestoff;
                pw.println(lm.navn + ",vanedannende," + lm.pris + "," + x + "," + lm.hentStyrke() );
            }
        }
        pw.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege lege : legeListe){            
                pw.println(lege.navn + "," + lege.hentKontrollID());
            }
        pw.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        for (Resept res : reseptListe){
            if (res instanceof MilitaerResept){
                pw.println(res.legemiddel.ID + "," + res.utskrivendeLege.hentNavn() + "," + res.pasient.ID + ",militaer"  );
            }
            else if (res instanceof PResept){
                pw.println(res.legemiddel.ID + "," + res.utskrivendeLege.hentNavn() + "," + res.pasient.ID + ",p," +res.reit   );
            }
            else if (res instanceof HvitResept){
                pw.println(res.legemiddel.ID + "," + res.utskrivendeLege.hentNavn() + "," + res.pasient.ID + ",hvit," + res.reit  );
            }
           
            else{
                pw.println(res.legemiddel.ID + "," + res.utskrivendeLege.hentNavn() + "," + res.pasient.ID + ",blaa," + res.reit  );
            }
        }

        pw.close();
        kommandoLokke();
    }

}



