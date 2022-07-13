
public class VanligLegemiddel extends Legemiddel {
    // lager denne fordi Legemiddel er abstrakt
    VanligLegemiddel(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }
}
