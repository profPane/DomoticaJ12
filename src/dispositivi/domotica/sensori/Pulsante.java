package dispositivi.domotica.sensori;

public class Pulsante extends Sensore {
    //attributi
    public Pulsante(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public void premi() { this.usa(); }

    public void usa() { 
        if (hub != null) hub.evento(this,"PUSH"); 
    }
}