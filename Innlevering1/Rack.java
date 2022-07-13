

class Rack {
    // private static int antallNoder = 12;
    public Node[] rack = new Node[12];

    public boolean fulltRack() {
        int teller = 0;
        for (Node node : rack) {
            if (node != null) {
                teller++;
            }
        }
        return teller >= 12;
    }

    public void settInnNodeIRack(Node node) {
        // legger inn node på første plass det ikke er null-verdi og returnerer
        for (int i = 0; i < 12; i++) {
            if (rack[i] == null) {
                rack[i] = node;
                return;
            }
        }
    }

    public int getAntNoder() {
        // teller alt som ikke er null-verdier
        int teller = 0;
        for (Node node : rack) {
            if (node != null) {
                teller++;
            }
        }
        return teller;
    }

    public int antProsessorer() {
        // kjører metode på alle plasseringer der det ikke er null-verdi
        int antallProsessorer = 0;
        for (Node node : rack) {
            if (node != null) {
                antallProsessorer += node.antProsessorer();
            }
        }
        return antallProsessorer;
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        //samme som i getAntNoder
        int antallNoderMedNokMinne = 0;
        for (Node node : rack) {
            if (node != null) {
                if (node.nokMinne(paakrevdMinne)) {
                    antallNoderMedNokMinne++;
                }
            }
        }
        return antallNoderMedNokMinne;
    }

}
