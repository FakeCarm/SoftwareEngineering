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
    private Shape shape;

    /**
     * Crea un'istanza di DragShape per spostare una forma nel foglio di disegno.
     * 
     * @param shapeEditor l'editor utilizzato per eseguire l'operazione di drag.
     * @param initialX
     * @param initialY
     * @param finalX
     * @param finalY
     */
    public DragShape(ShapeEditor shapeEditor,Shape shape, double initialX, double initialY, double finalX, double finalY) {
        this.shapeEditor = shapeEditor;
        this.initialX = initialX;
        this.initialY = initialY;
        this.finalX = finalX;
        this.finalY = finalY;
        this.shape = shape;
    }

    /**
     * Esegue il comando di spostamento (drag) della forma nel foglio di disegno.
     * Il comando aggiorna la posizione della forma applicando gli offset orizzontale e verticale.
     * Utilizza il metodo dragShape(double, double).
     */
    @Override
    public void execute() {
        shapeEditor.moveShapeTo(this.shape,finalX, finalY);
    }

    /**
     * Metodo per annullare il comando.
     */
    @Override
    public void undo() {
        shapeEditor.moveShapeTo(this.shape,initialX, initialY);
    }
    

    @Override
    public void redo() {
        execute();
    }
}
