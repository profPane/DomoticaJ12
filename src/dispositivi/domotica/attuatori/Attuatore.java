package dispositivi.domotica.attuatori;

import dispositivi.Dispositivo;
import dispositivi.domotica.altro.Hub;

public class Attuatore extends Dispositivo {

    private int id; //id assegnato
    private Hub hub; //riferimento all'HUB

    public Attuatore(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
    }

    public void associa(Hub hub) {
        this.id = hub.associa(this);
        this.hub = hub;
    }

    public int getID() {
        return this.id;
    }

    public void cambiaStato(){
        //segnaposto per cambiaStato da implementare nei singoli Dispositivi 
        //a seconda della loro logica di funzionamento
    }

    @Override
    public String toString() {
        String stato = "Non associato";
        if (hub != null) {
            stato = "Associato a " + hub.getSn();
        }
        return super.toString() + " - " +stato;
    }
}