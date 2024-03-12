package dispositivi;

public class Dispositivo {

    private String sn;
    private String marca;
    private String modello;
    private int carica; // -1 = alimentazione esterna, altrimenti 0-100 %

    public Dispositivo(String sn, String marca, String modello, int carica) {
        this.sn = sn;
        this.marca = marca;
        this.modello = modello;
        this.carica = carica;
    }

    @Override
    public String toString(){
        String carica = (this.carica!=-1)?this.carica+"%":"Alimentazione Esterna";
        return "SN: "+sn+" - marca: "+marca+" - Modello: "+modello+" - Carica: "+carica;
    };

    public int getCarica() {
        return carica;
    }

    public String getSn() {
        return this.sn;
    }
}