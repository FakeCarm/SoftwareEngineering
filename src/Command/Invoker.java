package Command;

import java.util.Stack;

/**
 * Classe che funge da invocatore per la gestione dei comandi.
 * 
 * La classe segue il pattern Singleton per assicurare che esista una sola istanza di Invoker in tutto il programma.
 * 
 * @author cassd
 */
public class Invoker {

    private Stack<Command> commandStack;
    private static Invoker invoker = null;

    /**
     * Costruttore privato per garantire che ci sia solo una singola istanza di Invoker.
     * Inizializza una nuova pila di comandi.
     */
    private Invoker() {
        commandStack = new Stack<>();
    }

    /**
     * Ottiene l'istanza unica di Invoker.
     * Se l'istanza non esiste, la crea e la restituisce.
     * 
     * @return l'istanza di Invoker.
     */
    public static Invoker getInvoker() {
        if (Invoker.invoker == null) {
            Invoker.invoker = new Invoker();
        }
        return invoker;
    }

    /**
     * Esegue un comando e lo inserisce nella pila di comandi.
     * Il comando viene eseguito immediatamente tramite il suo metodo execute().
     * 
     * @param command il comando da eseguire.
     */
    public void executeCommand(Command command) {
        this.commandStack.push(command);
        command.execute();
    }
}