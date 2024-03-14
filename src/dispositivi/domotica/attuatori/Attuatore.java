package dispositivi.domotica.attuatori;

import java.util.Arrays;

import dispositivi.Dispositivo;

public class Attuatore extends Dispositivo {
    
    public Attuatore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public String cambiaStato() { //passo al prossimo stato
        stato = (stato++)%stati.length; 
        return this.stato();
    }

    public String comando(Object comando){ //riceve il comando qualsiasi tipo sia
        //se finisco qui vuol dire che il tipo comando non è gestito dall'attuatore 
        return "FAIL";  //se non riesco a gestirlo restituisco fallimento
    }

    protected String stato(){
        return this.stati[this.stato];
    }

    public String comando(String comando){ //riceve il comando se è una Stringa 
        int posStato = Arrays.binarySearch(stati, comando); //lo cerco tra i possibili
        if (posStato!=-1) { //se c'è lo imposto
            this.stato=posStato;
            return this.stato();
        }   
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }


}