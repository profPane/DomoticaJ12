package dispositivi.domotica.attuatori;

public class Lamp extends Attuatore {

    //attributi
    private String stati[] = {"OFF", "ON"}; //stati possibili
    private int lux; //luminosita massima espressa in lux

    public Lamp(String sn, String marca, String modello, int carica, int lux) {
        super(sn, marca, modello,  carica);
        this.lux = lux;
        this.stato = 0;
    }

    @Override
    public String toString() {
        return super.toString()+" - Stato: " + this.stati[this.stato];
    }
}