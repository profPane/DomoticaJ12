package dispositivi.domotica.altro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dispositivi.Dispositivo;
import dispositivi.domotica.attuatori.*;
import dispositivi.domotica.sensori.Interruttore;
import dispositivi.domotica.sensori.Pulsante;
import dispositivi.domotica.sensori.Sensore;

public class Hub extends Dispositivo {

    public String stati[]={"OFF","ON"};

    private List<Dispositivo> dispositiviCollegati;
    private HashMap<Integer, Attuatore> collegamenti;
    private int progressivoID; //id contiene l'ultimo ID assegnato

    public Hub(String sn, String marca, String modello, int caricaMassima) {
        super(sn, marca, modello, caricaMassima);
        this.dispositiviCollegati = new ArrayList<>();
        progressivoID=-1;
    }

    public int associa(Dispositivo dispositivo) {
        dispositiviCollegati.add(dispositivo);
        return progressivoID++;
    }

    public void collega(int idSensore, int idAttuatore){
        for (Dispositivo dispositivo : dispositiviCollegati) {
            if (dispositivo instanceof Attuatore){ //è un attuatore
                Attuatore attuatore = (Attuatore) dispositivo;
                if (attuatore.getID()==idAttuatore){ //è quello che cerco
                    collegamenti.put(idSensore, attuatore);
                }
            } 
        }
    }

    public void riceviEvento(Sensore sensore, boolean stato) {
        //cerco l'eventuale Attuatore collegato a questo Sensore tramite ID nella lista dei collementi
        Attuatore attuatore = collegamenti.get(sensore.getID());
        if (attuatore==null) { System.err.println("Nessun attuatore collegato");}
        else { //c'è un attuatore collegato
            if (sensore instanceof Pulsante) {
                System.err.println("Pulsante premuto!");
                attuatore.cambiaStato();
            } else if (sensore instanceof Interruttore) {
                attuatore.setStato(stato);
                System.err.println("Interruttore " + (interruttore.isStato() ? "acceso" : "spento"));
            }
        }
    }

    public String getListaDispositiviCollegati() {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositiviCollegati) {
            sb.append(dispositivo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Informazioni sull'HUB:" +super.toString()+ "\nDispositivi collegati:\n" + getListaDispositiviCollegati();
    }
}
