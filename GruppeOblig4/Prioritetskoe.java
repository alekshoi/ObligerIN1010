public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    
    @Override
    public void leggTil(T x) {
        if (stoerrelse == 0) {
            super.leggTil(x);
            return;
        }

        Node sjekk = start;

        for (int i = 0; i < stoerrelse; i++) {
            if (i != 0) {
                sjekk = sjekk.neste;
            }
            if (sjekk.data.compareTo(x) > 0) {
                super.leggTil(i, x);
                return;
            }
        }
        super.leggTil(x);
    }
}
