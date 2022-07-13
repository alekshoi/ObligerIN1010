import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

class FletteTrad implements Runnable {

    private CountDownLatch fletteTradCounter;
    private CountDownLatch leseTradCounter;
    int id;
    Monitor2 monitor;

    FletteTrad(int id, Monitor2 monitor, CountDownLatch fletteTradCounter, CountDownLatch leseTradCounter) {
        this.monitor = monitor;
        this.id = id;
        this.fletteTradCounter = fletteTradCounter;
        this.leseTradCounter = leseTradCounter;
    }

    @Override
    public void run() {
        // Fletter til lesetråder er ferdig og ingen maps er igjen til å flettes
        while (true) {
            System.out.println(String.format("FletteTrad %s starter", this.id));
            try {
                HashMap<String, Subsekvens> flettetMap;
                boolean leseTraderFerdig = leseTradCounter.getCount() == 0;
                ArrayList<HashMap<String, Subsekvens>> mapsSomSkalSlaasSammen = monitor.hentUtTo(leseTraderFerdig);
                if (mapsSomSkalSlaasSammen.size() != 2) {
                    break;
                }
                flettetMap = Monitor2.slaaSammenMaps(mapsSomSkalSlaasSammen.get(0), mapsSomSkalSlaasSammen.get(1));

                monitor.settInnHashMap(flettetMap);

            } catch (Exception exception) {
                System.out.println(String.format("FletteTrad %s krasjet", this.id));
                exception.printStackTrace();
            }
        }
        System.out.println(String.format("FletteTrad %s ferdig", this.id));
        fletteTradCounter.countDown();
    }

}
