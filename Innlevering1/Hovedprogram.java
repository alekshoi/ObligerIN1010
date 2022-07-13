import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Hovedprogram {
    // lager standardfunksjon som gjør at du kan kalle på så mange Noder en vil, med
    // de egenskapene en selv ønsker.
    // gjør det lettere å legge til fra fil
    public static void lagNode(Dataklynge dataklynge, int antallNoder, int MinnePerNode, int AntallProsessorerPerNode) {
        for (int i = 0; i < antallNoder; i++) {
            Node node = new Node(MinnePerNode, AntallProsessorerPerNode);
            dataklynge.settInnNodeIDataklynge(node);
        }

    }

    public static void main(String[] args) {
        Dataklynge saga = new Dataklynge();

        // finner fil og gjør den klar for å bli lest
        Scanner fil = null;
        File filen = new File("data/dataklynge2.txt");
        try {
            fil = new Scanner(filen);
        } catch (FileNotFoundException exception) {
            System.out.println("Fant ikke filen");
            System.exit(1);
        }

        // tar info fra hver linje og bruker funksjonen over til å legge til noder. 
        String linje = "";
        while (fil.hasNextLine()) {
            linje = fil.nextLine();
            String[] biter = linje.split(" ");
            int antallNoder = Integer.parseInt(biter[0]);
            int AntallProsessorerPerNode = Integer.parseInt(biter[1]);
            int MinnePerNode = Integer.parseInt(biter[2]);
            lagNode(saga, antallNoder, MinnePerNode, AntallProsessorerPerNode);
        }
        fil.close();

        // skriver ut nødvendig/ønsket informasjon. 
        int antallNodermedMinst32GB;
        int antallNodermedMinst64GB;
        int antallNodermedMinst128GB;

        antallNodermedMinst32GB = saga.noderMedNokMinne(32);
        antallNodermedMinst64GB = saga.noderMedNokMinne(64);
        antallNodermedMinst128GB = saga.noderMedNokMinne(128);

        int antallProsessorerIDataklynge;
        int antallRack;
        antallProsessorerIDataklynge = saga.antProsessorer();
        antallRack = saga.hentDataKlynge().size();

        System.out.println("Noder med minst 32 GB: " + antallNodermedMinst32GB);
        System.out.println("Noder med minst 64 GB: " + antallNodermedMinst64GB);
        System.out.println("Noder med minst 128 GB: " + antallNodermedMinst128GB);
        System.out.println("Antall prosessorer: " + antallProsessorerIDataklynge);
        System.out.println("Antall rack: " + antallRack);

    }
}
