package dispositivi.domotica.sensori;

import dispositivi.Dispositivo;
import dispositivi.domotica.altro.Hub;

public class Sensore extends Dispositivo {

    private int id; //id assegnato
    private Hub hub; //riferimento all'HUB

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public Sensore(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
    }

    public void associa(Hub hub) {
        this.id = hub.associa(this);
        this.hub = hub;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        String stato = "Non associato";
        if (hub != null) {
            stato = "Associato a " + hub.getSn();
        }
        return super.toString() + " " + stato;
    }
}