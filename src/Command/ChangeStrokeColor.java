package Command;

import javafxapplication1.ShapeEditor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Classe che rappresenta un comando per cambiare il colore del tratto (stroke) di una forma.
 * Estende la classe Command
 * 
 * @author cassd
 */
public class ChangeStrokeColor extends Command {

    private Color strokeColor;
    private Color previousStrokeColor;
    private ShapeEditor editor;

    /**
     * Crea un'istanza di ChangeStrokeColor.
     * 
     * @param strokeColor il nuovo colore del tratto da applicare alla forma.
     * @param editor l'editor utilizzato per modificare il colore del tratto della forma.
     */
    public ChangeStrokeColor(ShapeEditor editor, Color strokeColor) {
        this.strokeColor = strokeColor;
        this.editor = editor;
    }

    /**
     * Esegue il comando per cambiare il colore del tratto della forma.
     * Cambia il colore del tratto utilizzando il metodo changeStrokeColor(Color).
     */
    @Override
    public void execute() {
        assert this.editor != null: "Editor: è nullo";
        assert this.strokeColor != null: "Colore non selezionato";
        previousStrokeColor = (Color) editor.getShape().getStroke();
        this.editor.changeStrokeColor(strokeColor);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        editor.changeStrokeColor(previousStrokeColor);
    }
    
    @Override
    public void redo() {
        execute();
    }

}
