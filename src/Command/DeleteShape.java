package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Classe che rappresenta un comando per rimuovere una forma dal foglio di disegno.
 * Estende la classe Command.
 * 
 * @author cassd
 */
public class DeleteShape extends Command {
    
    private Shape shape;
    private Paper drawingPaper;
    
    /**
     * Crea un'istanza di DeleteShape per rimuovere una forma dal foglio di disegno.
     * 
     * @param drawingPaper il foglio di disegno dal quale rimuovere la forma.
     * @param shape la forma da rimuovere.
     */
    public DeleteShape(Paper drawingPaper, Shape shape) {
        this.shape = shape;
        this.drawingPaper = drawingPaper;
    }

    /**
     * Esegue il comando di rimozione della forma dal foglio di disegno.
     
     * La forma viene rimossa dal {@link Paper} tramite il metodo removeOnPaper(Shape).
     */
    @Override
    public void execute() {
        assert this.drawingPaper != null : "AddShape: drawingPaper non deve essere null!";
        assert this.shape != null : "AddShape: shape non deve essere null";
        this.drawingPaper.removeOnPaper(this.shape);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        this.drawingPaper.addOnPaper(this.shape);
    }

    @Override
    public void redo() {
        execute();
    }
}
