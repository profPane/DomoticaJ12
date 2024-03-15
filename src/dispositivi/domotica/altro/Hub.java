package dispositivi.domotica.altro;

import java.util.HashMap;

import dispositivi.Dispositivo;
import dispositivi.domotica.attuatori.*;
import dispositivi.domotica.sensori.Sensore;

public class Hub extends Dispositivo {

    //lista dei dispositivi associati all'HUB
    private HashMap<Integer, Dispositivo> dispositivi;
    //lista dei collegamenti tra Sensori e Attuatori 
    private HashMap<Sensore, Attuatore> collegamenti; 
    private int progressivoID; //contiene l'ultimo ID assegnato ad un dispositivo

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

    public String collega(Sensore sensore, Attuatore attuatore){
        if (collegamenti.put(sensore, attuatore)==null) 
            return null; //la chiave non era già presente
        else 
            return "FAIL"; //
    }
    
    public String collegamenti(){
        StringBuilder collegati = new StringBuilder();
        collegamenti.forEach((sensore,attuatore)->{
            collegati.append("Il sensore: \n");
            collegati.append(sensore.getID());
            collegati.append("\n agisce su: \n");
            collegati.append(attuatore.toString());
            collegati.append("\n");
        });
        return collegati.toString();
    }

    //riceve e gestisce un EVENTO da un sensore
    //l'EVENTO è formato da sensore e dal comando ricevuto (es: "ON" se un interrutore è stato messo in "Acceso")
    public String evento(Sensore sensore, String comando) {
        //cerco l'eventuale Attuatore collegato a questo Sensore tramite ID nella lista dei collementi
        Attuatore attuatore = collegamenti.get(sensore);
        if (attuatore==null) { //controllo se il sensore e collegato ad un attuatore
            System.err.println("HUBLOG: Nessun attuatore collegato a "+sensore );
        }
        else { //c'è un attuatore collegato
            String response="FAIL";
            if (comando==null) { //non c'è un comando specifico allora HUB dice all'attuatore di cambiare stato
                response = attuatore.nextState(); //dipende dall'attuatore se ha una metodo cambia stato
            } else { //altrimenti prende il comando ricevuto e lo inoltra all'attuatore
                response = attuatore.command(comando);
            }
            System.err.println("HUBLOG: Evento da: "+sensore.getID()+" Comando: "+comando
                +" Azione su: "+attuatore.getID()+" esito "+response);
            return response;
        }
        return "FAIL";
    }

    //crea un report dei dispositivi connessi, anche solo di una Classe specifica
    @SuppressWarnings("rawtypes")
    public String report(Class classe) {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositivi.values()) {
            // se il dispositivo è della classe richiesta lo aggiunge al report
            if (classe.isInstance(dispositivo)) sb.append(dispositivo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Informazioni sull'HUB: " +super.toString()+ "\nDispositivi associati:\n" + report(Dispositivo.class);
    }
}
