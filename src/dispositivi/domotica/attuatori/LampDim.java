package dispositivi.domotica.attuatori;

public class LampDim extends Lamp {

    private int lux;

    public LampDim(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
        this.lux = 0;
    }

    public void setStato(boolean stato){
        lux = (stato)?100:0;
    }

    public void impostaLux(int lux) {
        if (lux == 0) {
            settaStato(false);
        } else {
            settaStato(true);
            this.lux = lux;
        }
    }

    public int getLux() {
        return lux;
    }

    //se cambio lo stato di una dimmerabile agisco di +10 sulla luminosità
    //dopo 100 c'è 0 cioè off
    public void cambiaStato() {
        if (lux==100) lux=0;
        else lux = lux+10;
    }

    protected String stato(){
        System.err.println("\nLux: "+this.lux);
        return (this.lux>0)?"ON":"OFF";
    }

    @Override
    public String toString() {

        return super.toString()+ " - Luminosità attuale: " + lux;
    }

}