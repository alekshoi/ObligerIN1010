public class IndeksertListe<T> extends Lenkeliste<T> {

    public void sett (int pos, T x) {
        Node ny = new Node(x);

        if (pos > stoerrelse - 1|| pos < 0) {
            throw new UgyldigListeindeks(pos);
        }
        
        if (pos == 0) {
            ny.neste = start.neste;
            ny.forrige = start.forrige;
            start = ny;
        }
        else {
            int teller = 0;
            Node sjekk = start;
            while (teller != pos) {
                sjekk = sjekk.neste;
                teller++;
            }
            ny.neste = sjekk.neste;
            ny.forrige = sjekk.forrige;
            if (sjekk == slutt) {
                slutt = ny;
            }
            sjekk.neste.forrige = ny;
            sjekk.forrige.neste = ny;
        }
    }

    public T fjern (int pos) throws UgyldigListeindeks {
        if (pos > stoerrelse - 1 || pos < 0){
            throw new UgyldigListeindeks(pos);
        }

        int teller = 0;
        Node sjekk = start;

        while (teller != pos){
            sjekk = sjekk.neste;
            teller++;
        }
        if (sjekk == start) {
            sjekk.neste.forrige = null;
        }
        else if (sjekk == slutt) {
            sjekk.forrige.neste = null;
        }
        else {
            sjekk.forrige.neste = sjekk.neste;
            sjekk.neste.forrige = sjekk.forrige;
        }

        stoerrelse -= 1;
        return sjekk.data;

    }
}
