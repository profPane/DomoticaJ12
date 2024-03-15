package dispositivi.domotica.attuatori;

import dispositivi.Dispositivo;

public class Attuatore extends Dispositivo {
    
    public Attuatore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    //l'Attuatore generico non risponde alla richiesta nextState()
    public String nextState(){ return "FAIL"; };    
    
    //l'Attuatore generico non risponde riceve comandi
    public String command(String comando){ return "FAIL"; };
    
    //l'Attuatore generico non ha uno stato
    //protected String stato(){ return "UNDEF";  }
}