package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Classe astratta che rappresenta un comando da eseguire su una forma su un foglio di disegno.
 * Ogni sottoclasse estende questa classe per implementare comandi specifici che agiscono su una forma.
 * 
 * @author cassd
 */
public abstract class Command {

    /**
     * Crea un nuovo comando che opera su una forma specifica su un foglio di disegno.
     * 
     * @param drawingPaper il foglio di disegno su cui operare.
     * @param shape la forma su cui agire.
     */
    
    /**
     * Esegue l'azione definita dal comando.
     * Ogni sottoclasse deve implementare questo metodo per specificare cosa fare con la forma.
     */
    public abstract void execute();

    /**
     * Annulla l'azione eseguita dal comando.
     * Ogni sottoclasse deve implementare questo metodo per definire come annullare il comando eseguito.
     */
    public abstract void undo();
    
    public abstract void redo();
}
