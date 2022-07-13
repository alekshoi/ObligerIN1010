public class Stabel<T> extends Lenkeliste<T>{
    
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);

        if (start == null) {
            slutt = ny;
        }
        else {
            start.forrige = ny;
            ny.neste = start;
        }
        start = ny;
        stoerrelse += 1;
    } 
}
