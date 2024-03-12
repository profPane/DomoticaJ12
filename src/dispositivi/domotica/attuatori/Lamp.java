package dispositivi.domotica.attuatori;
public class Lamp extends Attuatore {

    private boolean stato;

    public Lamp(String sn, String marca, String modello, int carica) {
        super(sn, marca, modello, carica);
        this.stato = false;
    }

    public void cambiaStato() {
        stato = !stato;
    }

    public void settaStato(boolean stato) {
        this.stato = stato;
    }

    public boolean isStato() {
        return stato;
    }

    protected String stato(){
        return (!this.stato)?"ON":"OFF";
    }

    @Override
    public String toString() {
        return super.toString()+" - ID: "+this.getID()+" - Stato: " + this.stato();
    }

}