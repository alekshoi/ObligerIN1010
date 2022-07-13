



abstract class Lenkeliste<T> implements Liste<T> {
    protected Node forste, siste;
    protected int storrelse = 0;

    protected class Node {
        Node neste, forrige;
        T data;

        public Node(T data) {
            this.data = data;
        }
        
    }

    @Override
    public int stoerrelse() {
        return storrelse;
    }

    @Override
    public void leggTil(T x) {
        Node nyNode = new Node(x);
        // hvis den er tom, legger vi til et element som, ifølge logikk, er både det
        // første og siste elementet
        if (storrelse == 0) {
            siste = nyNode;
            forste = nyNode;
            storrelse++;
            return;
        }

        // hvis den har størrelsen 0, er både første og siste like, og vi endrer siste
        // til å være den nye Noden
        // de får også nye pekere
        if (storrelse == 1) {
            siste = nyNode;
            siste.forrige = forste;
            forste.neste = siste;
            storrelse++;
            return;
        }
        // endrer siste element til å være nyNode og setter opprinnelig siste til å være nest sist
        siste.neste = nyNode;
        nyNode.forrige = siste;
        siste = nyNode;
        siste.neste = null;
        storrelse++;
    }

    @Override
    public T hent() {
        return forste.data;
    }

    @Override
    public T fjern() {

        Node elementSomBlirFjernet = forste;
        // kan ikke fjerne fra liste hvis den er tom. Kaster exception
        // kunne brukt try og except, men exception vil bare forekomme under dette tilfellet
        if (storrelse == 0) {
            throw new UgyldigListeindeks(0);
        }
        // tømmer listen hvis størrelsen er 1
        if (storrelse == 1) {
            forste = null;
            siste = null;
            storrelse--;
            return elementSomBlirFjernet.data;
        }

        // gjør at det opprinnelige siste-elementet er det eneste igjen. 
        // men dette er nå både det første og siste elementet
        if (storrelse == 2) {
            forste = siste;
            forste.neste = null;
            siste.forrige = null;
            storrelse--;
            return elementSomBlirFjernet.data;

        }
        // tar ut første element og setter nr 2 til å være første
        forste = forste.neste;
        forste.forrige = null;
        storrelse--;

        return elementSomBlirFjernet.data;
    }

    @Override
    public String toString() {
        Node tmp = forste;
        String info = "";
        if (forste == null)
            return "Listen er tom";
        while (tmp != null) {
            info += tmp.data + "\n";
            tmp = tmp.neste;
        }
        return info;
    }
}
