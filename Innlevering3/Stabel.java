


class Stabel<T> extends Lenkeliste<T>{
    @Override
    public void leggTil(T x) {
        // legger nyNode først i listen, men lager unntak hvis listen er tom eller inneholder 1 element. 
        Node nyNode = new Node(x);        
        if (stoerrelse() == 0){
            super.leggTil(x);
            return;
        }
        // lager pekere og setter forste til nyNode hvis storrelsen er 1.
        if (stoerrelse()==1){
            forste = nyNode;
            nyNode.neste = siste;
            siste.forrige = nyNode;
            storrelse++;
            return;
        }

        forste.forrige = nyNode;
        nyNode.neste = forste;
        forste = nyNode;
        storrelse++;
        
    }
}
