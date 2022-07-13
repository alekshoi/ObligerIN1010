import java.util.Iterator;

abstract class Lenkeliste<T> implements Liste<T>{
    
    class Node {
        Node neste = null;
        Node forrige = null;
        T data;
        Node (T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    class LenkelisteIterator implements Iterator<T> {

        private int pos = 0;
        // Node sjekk = start;

        // public LenkelisteIterator(Lenkeliste<T> i) {
        //     // this.sjekk = i.start;
        // } 

        public boolean hasNext() {
            // return sjekk.neste != null;
            return pos < stoerrelse();
        }

        public T next() {
            // if (this.hasNext()) {
            //     T data = sjekk.data;
            //     sjekk = sjekk.neste;
            //     return data;
            // }
            // return null;
            pos++;
            return hent(pos-1);
        }
    }

    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse;

    public int stoerrelse() {
        return stoerrelse;
    }

    public T hent (int pos) throws UgyldigListeindeks {
        if (pos > stoerrelse - 1 || pos < 0){
            throw new UgyldigListeindeks(pos);
        }
        
        int teller = 0;
        Node sjekk = start;

        while (teller != pos) {
            sjekk = sjekk.neste;
            teller++;
        }
        return sjekk.data;

    }

    public void leggTil(T x) {
        Node ny = new Node(x);

        if (start == null) {
            start = ny;
        }
        else {
            ny.forrige = slutt;
            slutt.neste = ny;
        }
        slutt = ny;
        stoerrelse += 1;
    }

    public void leggTil (int pos, T x) throws UgyldigListeindeks{
        Node ny = new Node(x);

        if (pos > stoerrelse || pos < 0) {
            throw new UgyldigListeindeks(pos);
        }
        else if (pos == stoerrelse){
            this.leggTil(x);
            return;
        }
        else {
            if (pos == 0) {
                start.forrige = ny;
                ny.neste = start;
                start = ny;
                stoerrelse += 1;
            }
            else {
                int teller = 0;
                Node sjekk = start;
                while (teller != pos) {
                    sjekk = sjekk.neste;
                    teller++;
                }
                ny.forrige = sjekk.forrige;
                ny.neste = sjekk; 
                sjekk.forrige.neste = ny;
                sjekk.forrige = ny;
                stoerrelse += 1;
            }

        }
    }

    public T hent() {
        return start.data;
    }

    public T fjern() throws UgyldigListeindeks{
        if (stoerrelse == 0) {
            throw new UgyldigListeindeks(0);
        }
        T returverdi = start.data;
        if (stoerrelse == 1) {
            start = null;
            slutt = null;
        }
        else {
            start.neste.forrige = null;
            start = start.neste;
        }
        stoerrelse -= 1;
        return returverdi;
    }

    @Override
    public String toString() {
        String streng = "";

        // streng += "Antall noder: " + stoerrelse;
        // streng += "\nInnhold: ";

        Node pil = start;
        for (int i = 0; i < stoerrelse; i++) {
            // streng += "\n pos = " + String.valueOf(i) + ", data = ";
            if (i != 0) {
                pil = pil.neste;
            }
            streng += pil.toString();
        }
        return streng;
    }

    public Iterator<T> iterator() {
        return new LenkelisteIterator();
    }
}
