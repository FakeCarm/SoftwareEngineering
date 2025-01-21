/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafxapplication1.ShapeEditor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
/**
 *
 * @author cassd
 */
public class ChangeStrokeColor extends Command{
    
    private Color strokeColor;
    private ShapeEditor editor;

   

    public ChangeStrokeColor(Paper drawingPaper, Shape shape, Color strokeColor, ShapeEditor editor) {
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
        this.editor.changeStrokeColor(strokeColor);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
