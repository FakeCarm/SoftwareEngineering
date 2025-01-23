package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Questa classe rappresenta un comando che aggiunge una forma al foglio di disegno.
 * Estende la classe astratta {Command}.
 * 
 * Il comando si occupa di aggiungere una Shape al  Paper fornito come argomento durante l'inizializzazione della classe.
 * 
 * @author cassd
 */
public class AddShape extends Command {

    /**
     * Crea un'istanza di AddShape che aggiunge una forma al foglio di disegno.
     *
     * @param drawingPaper il foglio di disegno dove sarà aggiunta la forma. Deve essere un oggetto non null.
     * @param shape la forma da aggiungere al foglio di disegno. Deve essere un oggetto non null.
     */
    public AddShape(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
    }

    /**
     * Esegue il comando di aggiungere una forma al foglio di disegno.
     * 
     * Verifica che drawingPaper e shape}non siano null prima di aggiungere la forma al foglio.
     * Se uno dei parametri è nullo, viene sollevata un'asserzione.
     * La forma viene aggiunta al Paper tramite il metodo Paper#addOnPaper(Shape).
     */
    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShape: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        super.drawingPaper.addOnPaper(super.shape);
    }

    /**
     * Metodo per annullare il comando. Attualmente non implementato.
     * Questo metodo potrebbe essere utilizzato per rimuovere la forma dal foglio di disegno, 
     * ma non ha alcuna azione in questa versione della classe.
     */
    @Override
    public void undo() {
        assert super.drawingPaper != null : "AddShape undo: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape undo: shape non deve essere null!";
        super.drawingPaper.removeOnPaper(super.shape);
    }
    
    @Override
    public void redo() {
        //execute(); // Aggiunge nuovamente la figura al foglio di disegno
    }


}
