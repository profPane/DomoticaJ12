package dispositivi.domotica.altro;

import java.util.HashMap;

import dispositivi.Dispositivo;
import dispositivi.domotica.attuatori.*;
import dispositivi.domotica.sensori.Sensore;

public class Hub extends Dispositivo {

    private HashMap<Integer, Dispositivo> dispositivi;
    private HashMap<Integer, Attuatore> collegamenti;
    private int progressivoID; //id contiene l'ultimo ID assegnato

    public Hub(String sn, String marca, String modello) {
        super(sn, marca, modello, -1);
        this.dispositivi = new HashMap<>();
        this.collegamenti = new HashMap<>();
        progressivoID=-1;
    }

    public int associa(Dispositivo dispositivo) {
        dispositivi.put((++progressivoID), dispositivo);
        return progressivoID;
    }

    public String collega(int idSensore, int idAttuatore){
        Dispositivo dispositivo = dispositivi.get(idAttuatore);
        if (dispositivo instanceof Attuatore){ //è un attuatore
                collegamenti.put(idSensore, (Attuatore) dispositivo);
                return "OK";
        } 
        return "FAIL";
    }
    
    public String collegamenti(){
        StringBuilder collegati = new StringBuilder();
        collegamenti.forEach((id,att)->{
            collegati.append("Il sensore: \n");
            collegati.append(dispositivi.get(id).toString());
            collegati.append("\n agisce su: \n");
            collegati.append(att.toString());
            collegati.append("\n");
        });
        return collegati.toString();
    }

    public String evento(Sensore sensore, String comando) {
        //cerco l'eventuale Attuatore collegato a questo Sensore tramite ID nella lista dei collementi
        Attuatore attuatore = collegamenti.get(sensore.getID());
        if (attuatore==null) { 
            System.err.println("HUBLOG: Nessun attuatore collegato a "+sensore );
        }
        else { //c'è un attuatore collegato
            String response="FAIL";
            if (comando.equals("PUSH")) { //se è un pulsate uso cambiaStato()
                response = attuatore.cambiaStato();
            } else { //prende il comando ricevuto e lo inoltra all'attuatore
                response = attuatore.comando(comando);
            }
            System.err.println("HUBLOG: Evento su "+sensore+" Comando: "+comando );
            System.err.println("HUBLOG: Risultato evento su "+response);
            return response;
        }
        return "FAIL";
    }

    public String listaDispositivi(Class tipo) {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositivi.values()) {
           if (tipo.isInstance(dispositivo)) sb.append(dispositivo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Informazioni sull'HUB: " +this.getSn()+ "\nDispositivi associati:\n" + listaDispositivi(Dispositivo.class);
    }
}
