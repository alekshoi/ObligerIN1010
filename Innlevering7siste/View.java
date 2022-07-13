import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class View extends JPanel{
    // storrelse vindu skal være 600*400
    private final int breddeVindu = 450;
    private final int hoydeVindu = 400;
    private final int hoydeOverstePanel = 100;
    private final int antallRuterLengde = 12;
    private final int antallRuterBredde = 12;
    private final int antallSkatter = 10;
    private final int dimensjonerRute = 12;

    private Controller kontroll;
    private JFrame vindu;
    private JPanel hovedpanel, rutenett, overstepanel, kontrollpanel, info;
    private JLabel lengdeSlange;
    private JButton slutt, opp, ned, venstre, hoyre;
    private JLabel ruter[][] = new JLabel[antallRuterBredde][antallRuterLengde];

    View(Controller kontroll) {
        // sjekker dette da man f.eks. kan finne på å endre det grunnleggende øverst
        // her.
        if (antallRuterBredde != antallRuterLengde) {
            System.out.println("Rutenettet må være et kvadrat. Prøv igjen!");
            System.exit(1);
        }
        this.kontroll = kontroll;
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }
        // her er forsøket med å bruke piltastene på keyboardet
        /*
        // forsøkte også å extende JPanel 
        class MyKeyAdapter extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent e) {
                int knapp = e.getKeyCode();
                if(knapp == KeyEvent.VK_UP){
                    kontroll.endreRetning("opp");
                }
                if(knapp == KeyEvent.VK_DOWN){
                    kontroll.endreRetning("ned");
                }
                if(knapp == KeyEvent.VK_LEFT){
                    kontroll.endreRetning("venstre");
                }
                if(knapp == KeyEvent.VK_RIGHT){
                    kontroll.endreRetning("hoyre");
                }   
            }
        }
        this.addKeyListener(new MyKeyAdapter());
        */
        
        // lager metoder for de ulike knappene i øverste panelet
        class Avslutt implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.avsluttSpill();
            }

        }
        class Opp implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.endreRetning("opp");
            }
        }
        class Ned implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.endreRetning("ned");
                ;
            }
        }
        class Venstre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.endreRetning("venstre");
            }
        }
        class Hoyre implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroll.endreRetning("hoyre");
            }
        }

        // lager layout for spillet
        vindu = new JFrame("Snake");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // vi lager et hovedpanel som inneholder to paneler, der det ene er panelet
        // øverst og det andre er rutenettet.
        hovedpanel = new JPanel();
        vindu.add(hovedpanel);

        // til alle panel setter vi layout, border og plassering.
        // lager et panel for det som skal være øverst på siden
        overstepanel = new JPanel();
        overstepanel.setLayout(new BorderLayout());
        overstepanel.setPreferredSize(new Dimension(breddeVindu, hoydeOverstePanel));
        hovedpanel.add(overstepanel, BorderLayout.NORTH);

        // lager panelet som skal være lengst til venstre, og skal inneholde lengde på
        // slangen
        info = new JPanel();
        info.setLayout(new BorderLayout());
        info.setPreferredSize(new Dimension(breddeVindu / 3, hoydeOverstePanel));
        info.setBorder(BorderFactory.createLineBorder(Color.gray, 2));

        overstepanel.add(info, BorderLayout.WEST);

        // lager panelet som skal være i midten, med 4 knapper (opp, ned, venstre,
        // hoyre)
        kontrollpanel = new JPanel();
        kontrollpanel.setLayout(new BorderLayout());
        kontrollpanel.setPreferredSize(new Dimension(breddeVindu / 3, hoydeOverstePanel));
        overstepanel.add(kontrollpanel, BorderLayout.CENTER);

        // lager panelet som skal være til høyre med funksjonalitet til å avslutte
        // spillet
        slutt = new JButton("Avslutt spillet");
        slutt.setLayout((new BorderLayout()));
        slutt.addActionListener(new Avslutt());
        slutt.setPreferredSize(new Dimension(breddeVindu / 3, hoydeOverstePanel));
        overstepanel.add(slutt, BorderLayout.EAST);

        // legger til labelet i infopanelet til venstre
        lengdeSlange = new JLabel("Lengde: 0");
        info.add(lengdeSlange);
    

        // legger til navigeringsknappene i kontrollpanelet i midten
        // starter med å lage knappene og gi dem funksjonalitet
        opp = new JButton("Opp");
        opp.addActionListener(new Opp());
        

        ned = new JButton("Ned");
        ned.addActionListener(new Ned());

        venstre = new JButton("Venstre");
        venstre.addActionListener(new Venstre());

        hoyre = new JButton("Hoyre");
        hoyre.addActionListener(new Hoyre());

        // legger knappene til i panelet
        kontrollpanel.add(opp, BorderLayout.NORTH);
        kontrollpanel.add(ned, BorderLayout.SOUTH);
        kontrollpanel.add(venstre, BorderLayout.WEST);
        kontrollpanel.add(hoyre, BorderLayout.EAST);

        // lager rutenett-panel
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(dimensjonerRute, dimensjonerRute));
        rutenett.setPreferredSize(new Dimension(breddeVindu, hoydeVindu - hoydeOverstePanel));
        rutenett.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        hovedpanel.add(rutenett, BorderLayout.SOUTH);
        for (int x = 0; x < antallRuterBredde; x++) {
            for (int y = 0; y < antallRuterLengde; y++) {
                JLabel rute = new JLabel("", SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                ruter[x][y] = rute;
                rute.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
                rutenett.add(rute);
            }
        }

        // gjør vindu synlig
        vindu.setSize(breddeVindu, hoydeVindu);
        vindu.setVisible(true);
    }

    public void startBrett() {
        // iniatilserer startbrett
        // lager tomt rutenett
        this.tegnTomtRutenett();

        // legger slangen i midten
        int midtKoordinatX = antallRuterLengde / 2;
        int midtKoordinatY = antallRuterBredde / 2;
        ruter[midtKoordinatX][midtKoordinatY].setText("o");
        ruter[midtKoordinatX][midtKoordinatY].setForeground(Color.GREEN);
        
        kontroll.hentModell().hentSlange().leggTil(new Slange(midtKoordinatX, midtKoordinatY, "ned"));

        // legger til 
        int antallSkatterSomSkalLeggesTil = antallSkatter;
        while(antallSkatterSomSkalLeggesTil>0){
            Random rand = new Random();
            int randomRad = rand.nextInt(antallRuterBredde);
            int randomKol = rand.nextInt(antallRuterLengde);
            if (ruter[randomRad][randomKol].getText() == "") {
                ruter[randomRad][randomKol].setText("$");
                ruter[randomRad][randomKol].setForeground(Color.red);
                Skatt skattSomErLagtTil = new Skatt(randomRad, randomKol);
                kontroll.hentModell().leggTilSkatt(skattSomErLagtTil);
                antallSkatterSomSkalLeggesTil--;
            }
        }
    }

    public void oppdaterBrett() {
        // det ser ut som at slangen beveger seg fordi man lager et nytt rutennet hver
        // gang.
        Koe<Slange> slangen = kontroll.hentModell().hentSlange();
        Skatt[] skatter = kontroll.hentModell().hentSkatter();
        // lager vanlig rutenett
        tegnTomtRutenett();
        // legger inn skatter
        tegnInnSkatter(skatter);
        // lager slangen
        tegnSlangen(slangen);

        lengdeSlange.setText("Lengde: " + kontroll.hentModell().hentSlange().stoerrelse());

    }
    // oppretter tomme ruter
    private void tegnTomtRutenett() {
        for (int rad = 0; rad < antallRuterLengde; rad++) {
            for (int kolonne = 0; kolonne < antallRuterBredde; kolonne++) {
                ruter[rad][kolonne].setText("");
            }
        }
    }

    private void tegnInnSkatter(Skatt[] skatter) {
        // legger til skatter på koordinatene deres
        for (int i = 0; i < skatter.length; i++) {
            if (skatter[i] != null) {
                int skattx = skatter[i].hentRad();
                int skatty = skatter[i].hentKolonne();
                ruter[skattx][skatty].setText("$");
                ruter[skattx][skatty].setForeground(Color.red);
            }
        }
    }

    private void tegnSlangen(Koe<Slange> slangen) {
        for (int i = 0; i < slangen.stoerrelse(); i++) {
            int rad = slangen.hent(i).hentRad();
            int kol = slangen.hent(i).hentKolonne();
            ruter[rad][kol].setForeground(Color.GREEN);
            if (i == 0) {
                ruter[rad][kol].setText("o");
            } else {
                ruter[rad][kol].setText("+");

            }
        }
    }

    public int hentAntallKolonner() {
        return antallRuterBredde;
    }

}