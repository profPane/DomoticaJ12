package dispositivi.domotica.sensori;

import dispositivi.Dispositivo;

public class Sensore extends Dispositivo {

    public Sensore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public String event(Object evento){ //di qualsiati tipo sia il comando
        //segnaposto per la gestione dell'evento che è diversa per ogni sensore
        return "FAIL"; 
    }
}