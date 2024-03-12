package dispositivi.domotica.sensori;

public class Interruttore extends Sensore {
    private boolean stato;

    public Interruttore(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
        this.stato = false;
    }

    public void premi() {
        stato = !stato;
        if (super.getHub() != null) {
            super.getHub().riceviEvento(this,this.stato);
        }
    }

    public boolean isStato() {
        return stato;
    }

    @Override
    public String toString() {
        String stato = (!this.stato)?"ON":"OFF";
        return super.toString()+" - " + stato;
    }
}