package dispositivi.domotica.sensori;

public class Pulsante extends Sensore {
    //attributi
    public Pulsante(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
    }

    public void premi() { //simula la pressione del pulsante
        //quindi viene avvertito l'HUB dell'evento con una descrizione testuale "comando"
        this.evento("PUSH");
    }

    public String evento(String evento) { //gestisce un evento nel sensore
        if (evento.equals("PUSH")) { //il pulsante risponde solo al comando "PUSHED"
            if (hub != null) return hub.evento(this, null);
        }   
        //to do: controllo se stato contiene un numero tra 0 e 1 in stato e lo uso come stato
        return "FAIL"; //se non riesco a gestirlo restituisco fallimento
    }
}