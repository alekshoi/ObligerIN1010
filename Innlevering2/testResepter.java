class testResepter {
    public static void main(String[] args) {
        // vurderer det som unødvendig å legge testene ut i metoder, når jeg har endret toString() metode. 
        // oppretter nødvendige varibaler for å teste. 
        NarkotiskLegemiddel heroin = new NarkotiskLegemiddel("heroin", 100, 10.32, 1000);
        VanligLegemiddel paracet = new VanligLegemiddel("paracet", 10, 5.2);
        Lege erik = new Lege("erik");
        MilitaerResept resept1 = new MilitaerResept(heroin, erik, 1);
        PResept resept2 = new PResept(paracet, erik, 2, 1);

        System.out.println(resept1);
        
        // kjører assert tester.
        assert resept1.prisAaBetale() == 0;
        assert resept2.prisAaBetale() == 0;
        paracet.settNyPris(181);
        assert resept2.prisAaBetale() == 1;
        resept1.bruk();

        assert resept2.bruk() == false;
        assert resept1.hentLege() == erik;
        assert resept1.hentLegemiddel() == heroin;

    }
}
