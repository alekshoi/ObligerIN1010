import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Monitor1 extends SubsekvensRegister {
    static Lock laas = new ReentrantLock();

    // henter supermetoder, men setter lås på
    @Override
    public HashMap<String, Subsekvens> hentUtHashMap() {
        laas.lock();
        try {
            return super.hentUtHashMap();
        } finally {
            laas.unlock();
        }
    }

    public static HashMap<String, Subsekvens> LesFraFil(String filnavn) {
        laas.lock();
        try {
            return SubsekvensRegister.LesFraFil(filnavn);
        } finally {
            laas.unlock();
        }
    }
}
