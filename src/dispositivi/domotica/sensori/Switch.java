package dispositivi.domotica.sensori;

import java.util.Arrays;

public class Switch extends Sensore {

    //attributi
    public String stati[] = {"OFF", "ON"}; //stati possibili
    
    public Switch(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
        this.stato = 0; //stato OFF
    }

    public String stato(){ 
        return stati[stato]; 
    }

    public String evento(String evento) { //gestisce un evento nel sensore
        int posStato = Arrays.binarySearch(stati, evento); //lo cerco tra i possibili
        if (posStato!=-1) { //se c'è lo imposto
            this.stato=posStato;
            if (hub != null) return hub.evento(this, stati[this.stato]);;
        }   
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }

    @Override
    public String toString() {
        return super.toString()+" - " + this.stati[stato];
    }
}