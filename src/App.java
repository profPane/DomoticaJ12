import dispositivi.domotica.altro.Hub;
import dispositivi.domotica.attuatori.LampDim;
import dispositivi.domotica.attuatori.Attuatore;
import dispositivi.domotica.attuatori.Lamp;
import dispositivi.domotica.sensori.Switch;
import dispositivi.domotica.sensori.Pulsante;

public class App {

    public static void main(String[] args) {
        //creo l'HUB
        Hub hub = new Hub("HUB01", "Domotica", "1.0");

        //creo quattro dispositivi e li registro nell'HUB
        Pulsante pulsante = new Pulsante("XXa33", "Bticino", "MyHome", 100);
        pulsante.associa(hub);
        Switch interruttore = new Switch("12345", "Gewiss", "Chorus", 100);
        interruttore.associa(hub);
        Lamp lamp = new Lamp("54321", "Philips", "Hue", -1, 800);
        lamp.associa(hub);
        LampDim lampDimm= new LampDim("77t11", "IKEA", "Tradfri", -1, 1200);
        lampDimm.associa(hub);

        // Stampa informazioni sui dispositivi prima di simulareclke
        System.out.println(hub);

        hub.collega(pulsante, lamp);
        hub.collega(interruttore,lampDimm);

        System.out.println(hub.collegamenti());

        System.err.println("LOG: Lista attuatori \n"+hub.report(Attuatore.class));

        // Simulazione di pressione del pulsante
        pulsante.premi();
        // L'interruttore cambia stato
        interruttore.evento("ON");
        //porto la lampada dimmerabile a livello 59
        hub.evento(interruttore,"50");

        // Stampa informazioni sui dispositivi
        System.out.println(hub);
    }
}