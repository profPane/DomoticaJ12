package dispositivi.domotica.attuatori;

import dispositivi.Dispositivo;

public class Attuatore extends Dispositivo {
    
    public Attuatore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public String cambiaStato() { //passo al prossimo stato
        //segnaposto, ogni attuatore lo gestisce diversamente
        return "FAIL";
    }

    public String comando(Object comando){ //riceve il comando qualsiasi tipo sia
        //SEGNAPOSTO se finisco qui vuol dire che il tipo comando non è gestito dall'attuatore 
        return "FAIL";  //se non riesco a gestirlo restituisco fallimento
    }

    public String comando(String comando){ //riceve il comando tipo String
        //SEGNAPOSTO se finisco qui vuol dire che il tipo comando non è gestito dall'attuatore 
        return "FAIL";  //se non riesco a gestirlo restituisco fallimento
    }

    protected String stato(){
        return "FAIL";
    }
}