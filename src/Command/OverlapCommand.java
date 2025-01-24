/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 *
 * @author cassd
 */
public class OverlapCommand extends Command{
    
    private ShapeEditor editor;
    
    public OverlapCommand(Paper drawingPaper, Shape shape, ShapeEditor editor) {
        super(drawingPaper, shape);
        this.editor = editor;
    }

    @Override
    public void execute() {
        this.editor.overlap();
    }

    @Override
    public void undo() {
        
    }

    @Override
    public void redo() {
        
    }
    
}
