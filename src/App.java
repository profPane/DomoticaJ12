import dispositivi.domotica.altro.Hub;
import dispositivi.domotica.attuatori.LampDim;
import dispositivi.domotica.attuatori.Attuatore;
import dispositivi.domotica.attuatori.Lamp;
import dispositivi.domotica.sensori.Interruttore;
import dispositivi.domotica.sensori.Pulsante;

public class App {

    public static void main(String[] args) {
        //creo l'HUB
        Hub hub = new Hub("HUB01", "Domotica", "1.0");

        //creo quattro dispositivi e li registro
        Pulsante pulsante1 = new Pulsante("XXa33", "Bticino", "MyHome", 100);
        pulsante1.associa(hub);
        Interruttore interruttore1 = new Interruttore("12345", "Gewiss", "Chorus", 100);
        interruttore1.associa(hub);
        Lamp lamp = new Lamp("54321", "Philips", "Hue", -1, 800);
        lamp.associa(hub);
        LampDim lampDim1 = new LampDim("77t11", "IKEA", "Tradfri", -1, 1200);
        lampDim1.associa(hub);

        // Stampa informazioni sui dispositivi prima di simulareclke
        System.out.println(hub);

        hub.collega(pulsante1.getID(), lamp.getID());
        hub.collega(interruttore1.getID(),lampDim1.getID());

        System.out.println(hub.collegamenti());

        System.err.println("LOG: Lista attuatori \n"+hub.listaDispositivi(Attuatore.class));

        // Simulazione di pressione del pulsante
        pulsante1.premi();
        // L'interruttore cambia stato
        interruttore1.evento("50");
        
        // Stampa informazioni sui dispositivi
        System.out.println(hub);
    }
}
