public class testLegemiddel {
    public static void main(String[] args) {
        // vurderer det som unødvendig å legge testene ut i metoder, når jeg har endret toString() metode. 
        NarkotiskLegemiddel heroin = new NarkotiskLegemiddel("Heroin", 1000, 10.2, 1000);
        VanedannendeLegemiddel paracet = new VanedannendeLegemiddel("Paracet", 10, 2.1, 10);

        System.out.println(heroin);
        System.out.println(paracet);

        heroin.settNyPris(90);

        // kjører assert metoder
        assert heroin.hentPris() == 90;
        assert heroin.hentNavn() == "Heroin";
        assert heroin.hentID() == 1;

        assert paracet.hentVanedannendeStyrke() == 10;
        assert paracet.hentNavn() == "Paracet";
        assert paracet.hentID() == 1;
        
    }
}
