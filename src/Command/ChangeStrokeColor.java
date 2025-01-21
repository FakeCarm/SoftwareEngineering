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
    private ShapeEditor editor;

    /**
     * Crea un'istanza di ChangeStrokeColor.
     * 
     * @param drawingPaper il foglio di disegno contenente la forma da modificare.
     * @param shape la forma di cui cambiare il colore del tratto.
     * @param strokeColor il nuovo colore del tratto da applicare alla forma.
     * @param editor l'editor utilizzato per modificare il colore del tratto della forma.
     */
    public ChangeStrokeColor(Paper drawingPaper, Shape shape, Color strokeColor, ShapeEditor editor) {
        super(drawingPaper, shape);
        this.strokeColor = strokeColor;
        this.editor = editor;
    }

    /**
     * Esegue il comando per cambiare il colore del tratto della forma.
     * Cambia il colore del tratto utilizzando il metodo changeStrokeColor(Color).
     */
    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShape: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        assert this.editor != null: "Editor: è nullo";
        assert this.strokeColor != null: "Colore non selezionato";
        this.editor.changeStrokeColor(strokeColor);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
