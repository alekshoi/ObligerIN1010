// synes ikke det var nødvendig å importere iterable, pluss ekstra interface

class IndeksertListe <T> extends Lenkeliste<T> implements interIndeksertListe<T>{
    

    @Override
    public void leggTil(int pos, T x) {
        Node nyNode = new Node(x);
        int ix = pos - 1;
        // sjekker at oppgitt posisjon er innenfor lovlig område
        // kunne brukt try og except, men exception vil bare forekomme under dette tilfellet
        if (pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        
        // super metode setter første of siste til å være nyNode
        if (pos==stoerrelse()){
            super.leggTil(x);
            return;
        }

        Node tmp = forste;
        if (pos==0){
            if (stoerrelse()==0){
                forste = nyNode;
                siste = nyNode;
                storrelse++;
                return;
            }
            forste.forrige = nyNode;
            nyNode.neste = forste;
            forste = nyNode;
            storrelse++;
            return;
        }
        
        for (int i = 0; i < stoerrelse(); i++) {
            if(i == ix) {
                nyNode.neste = tmp.neste;
                tmp.neste = nyNode;
                nyNode.forrige = tmp;
            }
            tmp = tmp.neste;
        }
        storrelse++;
    }

    @Override
    public void sett(int pos, T x) {
        Node nyNode = new Node(x);
        Node tmp = this.forste;
        
        // kan ikke endre element som ikke er i liste
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }

        // finner riktig element
        for (int i=0; i<pos; i++){
            tmp = tmp.neste;
        }
        //endrer elementet
        nyNode.forrige = tmp.forrige;
        nyNode.forrige.neste = nyNode;
        if(tmp != siste){
            nyNode.neste = tmp.neste;
            nyNode.neste.forrige = nyNode;
        }
        tmp = nyNode;
    }
    
    @Override
    public T hent(int pos) {
        if (pos < 0 || pos > stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node tmp = forste;
        for (int i=0; i<pos; i++){
            tmp = tmp.neste;
        }
        return tmp.data;
    }
    
    @Override
    public T fjern(int pos) {
        if (pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        
        // Søker etter riktig node å fjerne
        Node tmp = forste;
        for (int i=0; i<pos; i++){
            tmp = tmp.neste;
        }
        
        // Fjerner pekere til node som skal fjernes
        Node fjernetNode = tmp;
        System.out.println(tmp);
        if (fjernetNode.forrige != null) {
            fjernetNode.forrige = fjernetNode.neste;
        }
        if (fjernetNode.neste != null){
            fjernetNode.neste.forrige = fjernetNode.forrige;
        }
        
        storrelse--;
        return fjernetNode.data;
    }

}
