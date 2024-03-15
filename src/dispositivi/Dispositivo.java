package dispositivi;

import dispositivi.domotica.altro.Hub;

public class Dispositivo {

    private String sn;
    private String marca;
    private String modello;
    protected String stati[] = {"undefined"}; //stati possibili
    protected int id; //id assegnato
    protected int stato;
    protected int carica;
    protected Hub hub; //riferimento all'HUB

    public Dispositivo(String sn, String marca, String modello, int carica) {
        this.sn = sn;
        this.marca = marca;
        this.modello = modello;
        this.carica = 100;
        this.stato = 1; // stato undefined
    }

    public int getID() { return this.id; }

    public String getSn() { 
        return this.sn;
    }

    public void associa(Hub hub) {
        this.id = hub.associa(this);
        this.hub = hub; // il sensore deve sapere a quale hub comunicare l'evento
    }

    @Override
    public String toString() {
        StringBuilder stato = new StringBuilder();
        stato.append("S/N: "+this.sn);
        stato.append(" - Marca: "+this.marca);
        stato.append(" - Modello: "+this.modello);
        stato.append((hub != null)?" - Associato a " + hub.getSn()+" - ID: "+this.id:"Non associato");
        stato.append(" - Carica: " + ((carica!=-1)?carica+"%":"Alimentazione Esterna"));
        return stato.toString();
    }
}