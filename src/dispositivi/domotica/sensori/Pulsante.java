package dispositivi.domotica.sensori;

import dispositivi.domotica.altro.Hub;

public class Pulsante extends Sensore {

    public Pulsante(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
    }

    public void associa(Hub hub) {
        super.setHub(hub);
    }

    public void premi() {
        if (super.getHub() != null) {
            (super.getHub()).riceviEvento(this, null);
        }
    }
}