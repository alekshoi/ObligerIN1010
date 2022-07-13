import java.util.Iterator;

// limer inn noe av Koe fra tidligere oblig
interface Liste <T> extends Iterable<T>{
    int stoerrelse ();
    void leggTil (T x);
    T hent ();
    T fjern ();
}


class Koe<T> implements Liste<T>{
    
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

    class KoeIterator implements Iterator<T> {
        private int pos = 0;
        public boolean hasNext() {
            return pos < stoerrelse();
        }

        public T next() {
            pos++;
            return hent(pos-1);
        }
    }

    protected Node start = null;
    protected Node slutt = null;
    protected int stoerrelse = 0;

    public int stoerrelse() {
        return stoerrelse;
    }

    public T hent (int pos)  {
        if (pos > stoerrelse - 1 || pos < 0){
            throw new IndexOutOfBoundsException(pos);
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
        // endrer til at man skal legge til først
        Node ny = new Node(x);

        if (start == null) {
            start = ny;
            slutt = ny;
        }
        if(stoerrelse()==1){
            start = ny;
            start.neste = slutt;
            slutt.forrige = start;
        }
        if(stoerrelse()>1){
            start.forrige = ny;
            ny.neste = start;
            start = ny; 
        }
        stoerrelse += 1;
    }
    public T hent() {
        // henter første elementet 
        return start.data;
    }

    public T fjern() {
        // fjerner siste elementet i køen
        if (stoerrelse == 0) {
            throw new IndexOutOfBoundsException(0);
        }
        T returverdi = start.data;
        if (stoerrelse == 1) {
            start = null;
            slutt = null;
        }
        else {
            slutt.forrige.neste = null;
            slutt = slutt.forrige;
        }
        stoerrelse -= 1;
        return returverdi;
    }

    @Override
    public String toString() {
        String streng = "";
        Node pil = start;
        for (int i = 0; i < stoerrelse; i++) {
            if (i != 0) {
                pil = pil.neste;
            }
            streng += pil.toString();
        }
        return streng;
    }

    public Iterator<T> iterator() {
        return new KoeIterator();
    }
}
