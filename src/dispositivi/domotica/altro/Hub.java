package dispositivi.domotica.altro;

import java.util.HashMap;

import dispositivi.Dispositivo;
import dispositivi.domotica.attuatori.*;
import dispositivi.domotica.sensori.Sensore;

public class Hub extends Dispositivo {

    private HashMap<Integer, Dispositivo> dispositivi;
    private HashMap<Integer, Attuatore> collegamenti;
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
            System.err.println("HUBLOG: Evento su "+sensore+" Comando: "+comando );
            System.err.println("HUBLOG: Risultato evento "+response);
            return response;
        }
        return "FAIL";
    }

    public String listaDispositivi(Class classe) {
        StringBuilder sb = new StringBuilder();
        for (Dispositivo dispositivo : dispositivi.values()) {
            // se il dispositivo è della classe richiesta lo aggiunge al report
            if (classe.isInstance(dispositivo)) sb.append(dispositivo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Informazioni sull'HUB: " +this.getSn()+ "\nDispositivi associati:\n" + listaDispositivi(Dispositivo.class);
    }
}
