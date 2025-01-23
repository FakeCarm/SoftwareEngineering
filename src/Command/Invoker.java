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

    private static Invoker instance;
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    
    private UndoRedoListener undoRedoListener;

    /**
     * Costruttore privato per garantire che ci sia solo una singola istanza di Invoker.
     * Inizializza una nuova pila di comandi.
     */
    private Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Ottiene l'istanza unica di Invoker.
     * Se l'istanza non esiste, la crea e la restituisce.
     * 
     * @return l'istanza di Invoker.
     */
    public static Invoker getInvoker() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }

    /**
     * Esegue un comando e lo inserisce nella pila di comandi.
     * Il comando viene eseguito immediatamente tramite il suo metodo execute().
     * 
     * @param command il comando da eseguire.
     */
    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Svuota lo stack di redo ogni volta che un nuovo comando viene eseguito
        notifyUndoRedoStateChanged();
    }
    
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            notifyUndoRedoStateChanged();
        }
    }
    
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            notifyUndoRedoStateChanged();
        }
    }
    
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
    
    public void setUndoRedoListener(UndoRedoListener listener) {
        this.undoRedoListener = listener;
    }
    
    private void notifyUndoRedoStateChanged() {
        if (undoRedoListener != null) {
            undoRedoListener.onUndoRedoStateChanged(canUndo(), canRedo());
        }
    }
    
    public static void resetInvoker() {
        instance = null;
    }

    
}