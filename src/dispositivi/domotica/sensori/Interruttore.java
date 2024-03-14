package dispositivi.domotica.sensori;

public class Interruttore extends Sensore {

    //attributi
    public String stati[] = {"OFF", "ON"}; //stati possibili
    
    public Interruttore(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
        this.stato = 0;
    }

    public String stato(){ 
        return stati[stato]; 
    }

    public void usa() { //simula l'uso del Sensore
        stato = (stato+1)%stati.length;
        if (hub != null) hub.evento(this, stato());
    }

    public void usa(String stato){
        
    }

    @Override
    public String toString() {
        return super.toString()+" - " + this.stati[stato];
    }
}