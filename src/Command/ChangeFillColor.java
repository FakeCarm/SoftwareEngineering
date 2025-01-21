/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 *
 * @author cassd
 */
public class ChangeFillColor extends Command{

    private Color strokeColor;
    private ShapeEditor editor;

   

    public ChangeFillColor(Paper drawingPaper, Shape shape, Color strokeColor, ShapeEditor editor) {
        super(drawingPaper, shape);
        this.strokeColor = strokeColor;
        this.editor = editor;
    }
    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShaoe: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        assert this.editor != null: "Editor: è nullo";
        assert this.strokeColor != null: "Colore non selezionato";
        this.editor.changeFillColor(strokeColor);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
