import java.util.ArrayList;

class Dataklynge {
    private int antallNoderPerRack;
    private ArrayList<Rack> racks = new ArrayList<Rack>();

    public void settInnNodeIDataklynge(Node node) {
        boolean sattInnNode = false;
        for (Rack rack : racks) {
            // finner rack med ledig plass og setter inn node på null-verdi sin plass.
            // slutter å lete hvis man allerede har lagt inn node.
            if (!rack.fulltRack() && !sattInnNode) {
                rack.settInnNodeIRack(node);
                sattInnNode = true;
            }
        }
        // hvis man ikke har lagt inn node betyr det at alle rack er fulle, og man må
        // lage nytt rack og legge noden i racket.

        if (!sattInnNode) {
            lagNyttRackOgLeggInnNode(node);
        }
        // kaller på den lokale metoden under
    }

    private void lagNyttRackOgLeggInnNode(Node node) {
        Rack rack = new Rack();
        rack.settInnNodeIRack(node);
        racks.add(rack);
    }

    public int antallNoderRack() {
        return antallNoderPerRack;
    }

    // går gjennom hvert rack og kaller på metoden for antall prossesorer i rack.
    // Legger summen av alle rackene sammen
    public int antProsessorer() {
        int antallProsessorerTotaltIDataklynge = 0;
        for (Rack rack : racks) {
            antallProsessorerTotaltIDataklynge += rack.antProsessorer();
        }
        return antallProsessorerTotaltIDataklynge;
    }

    // går gjennom hvert rack og kaller på metoden for antall noder med nok minne i
    // rack. Legger summen av alle rackene sammen
    public int noderMedNokMinne(int paakrevdminne) {
        int antallNoderMedNokMinne = 0;
        for (Rack rack : racks) {
            antallNoderMedNokMinne += rack.noderMedNokMinne(paakrevdminne);
        }
        return antallNoderMedNokMinne;
    }

    // trengs for å sjekke antall racks i dataklynge i hoveprogram
    public ArrayList<Rack> hentDataKlynge() {
        return racks;
    }

}
