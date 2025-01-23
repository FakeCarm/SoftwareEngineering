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
    private double initialX;
    private double initialY;
    private double finalX;
    private double finalY;

    /**
     * Crea un'istanza di DragShape per spostare una forma nel foglio di disegno.
     * 
     * @param drawingPaper il foglio di disegno contenente la forma da spostare.
     * @param shape la forma da spostare.
     * @param shapeEditor l'editor utilizzato per eseguire l'operazione di drag.
     * @param offsetX l'offset orizzontale per spostare la forma.
     * @param offsetY l'offset verticale per spostare la forma.
     */
    public DragShape(Paper drawingPaper, Shape shape, ShapeEditor shapeEditor, double initialX, double initialY, double finalX, double finalY) {
        super(drawingPaper, shape);
        this.shapeEditor = shapeEditor;
        this.initialX = initialX;
        this.initialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;
    }

    /**
     * Esegue il comando di spostamento (drag) della forma nel foglio di disegno.
     * Il comando aggiorna la posizione della forma applicando gli offset orizzontale e verticale.
     * Utilizza il metodo dragShape(double, double).
     */
    @Override
    public void execute() {
        shapeEditor.moveShapeTo(shape, finalX, finalY);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        shapeEditor.moveShapeTo(shape, initialX, initialY);
    }
    

    @Override
    public void redo() {
        execute();
    }
}
