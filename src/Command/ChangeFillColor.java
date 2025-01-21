package Command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 * Classe che rappresenta un comando per cambiare il colore di riempimento di una forma.
 * Estende la classe {@link Command}.
 * 
 * @author cassd
 */
public class ChangeFillColor extends Command {

    private Color strokeColor;
    private ShapeEditor editor;

    /**
     * Crea un'istanza di ChangeFillColor.
     * 
     * @param drawingPaper il foglio di disegno contenente la forma da modificare.
     * @param shape la forma di cui cambiare il colore di riempimento.
     * @param strokeColor il nuovo colore di riempimento da applicare alla forma.
     * @param editor l'editor utilizzato per modificare il colore della forma.
     */
    public ChangeFillColor(Paper drawingPaper, Shape shape, Color strokeColor, ShapeEditor editor) {
        super(drawingPaper, shape);
        this.strokeColor = strokeColor;
        this.editor = editor;
    }

    /**
     * Esegue il comando per cambiare il colore di riempimento della forma.
     * Cambia il colore di riempimento utilizzando il metodo changeFillColor(Color).
     */
    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShape: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        assert this.editor != null: "Editor: è nullo";
        assert this.strokeColor != null: "Colore non selezionato";
        this.editor.changeFillColor(strokeColor);
    }

    /**
     * Metodo per annullare il comando. Non implementato in questa versione della classe.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
