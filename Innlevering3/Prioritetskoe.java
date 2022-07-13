
class Prioritetskoe <T extends Comparable<T>> extends Lenkeliste<T> {
    
    @Override
    public void leggTil(T x){
        // lager unntak for når listen er tom eller listen har lengden 1. 
        if (stoerrelse()==0){
            super.leggTil(x);
            return;
        }
        Node nyNode = new Node(x);
        Node tmp = forste;
        if(stoerrelse() == 1){
            if (tmp.data.compareTo(nyNode.data)>0){
                forste = nyNode;
                siste.forrige = forste;
                forste.neste = siste;
                storrelse++;
                return;
            }
            super.leggTil(x);
            return;
        }
        // går gjennom liste
        while(tmp!=null){
            if(tmp.data.compareTo(nyNode.data)==0){
                storrelse++;
                // plasserer, og kaller nyNode, avhengig av hvor det like elementet befinner seg
                if(tmp.neste==null){
                    siste.neste = nyNode;
                    nyNode.forrige = siste;
                    siste = nyNode;
                    return;
                }
                if(tmp.forrige==null){
                    forste.forrige = nyNode;
                    nyNode.forrige = forste;
                    return;
                }
                tmp.neste.forrige = nyNode;
                nyNode.neste = tmp.neste;
                tmp.neste = nyNode;
                nyNode.forrige = tmp;
                return;
            }
            if (tmp.data.compareTo(nyNode.data)>0){
                // plasserer, og kaller nyNode, avhengig av det større elementet det befinner seg
                storrelse++;
                if (tmp.forrige==null){
                    nyNode.neste = forste;
                    forste.forrige = nyNode;
                    forste = nyNode;
                    return;
                }
                nyNode.forrige = tmp.forrige;
                tmp.forrige.neste = nyNode;
                tmp.forrige = nyNode;
                nyNode.neste = tmp;
                return;
            }
            tmp = tmp.neste;
        }
        // hvis man har gått gjennom hele koen uten å finne noe som er likt eller større er elementet som skal legges til større enn alle elementer i listen
        // man setter derfor det siste elementet til å være nyNode, og forskyver den opprinnelige siste en plass bakover. 
        siste.neste = nyNode;
        nyNode.forrige =siste;
        siste = nyNode;
        storrelse++;
    }
}
