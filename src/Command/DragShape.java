package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 * Classe che rappresenta un comando per spostare una forma (drag) all'interno del foglio di disegno.
 * Estende la classe Command.
 * 
 * @author cassd
 */
public class DragShape extends Command {
    
    private ShapeEditor shapeEditor;
    private double offsetX;
    private double offsetY;

    /**
     * Crea un'istanza di DragShape per spostare una forma nel foglio di disegno.
     * 
     * @param drawingPaper il foglio di disegno contenente la forma da spostare.
     * @param shape la forma da spostare.
     * @param shapeEditor l'editor utilizzato per eseguire l'operazione di drag.
     * @param offsetX l'offset orizzontale per spostare la forma.
     * @param offsetY l'offset verticale per spostare la forma.
     */
    public DragShape(Paper drawingPaper, Shape shape, ShapeEditor shapeEditor, double offsetX, double offsetY) {
        super(drawingPaper, shape);
        this.shapeEditor = shapeEditor;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Esegue il comando di spostamento (drag) della forma nel foglio di disegno.
     * Il comando aggiorna la posizione della forma applicando gli offset orizzontale e verticale.
     * Utilizza il metodo dragShape(double, double).
     */
    @Override
    public void execute() {
       shapeEditor.dragShape(this.offsetX, this.offsetY);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        // Implementazione mancante.
    }
}
