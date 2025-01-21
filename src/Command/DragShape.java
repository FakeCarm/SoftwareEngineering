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
public class DragShape extends Command{
    
    private ShapeEditor shapeEditor;
    private double offsetX;
    private double offsetY;
    
    public DragShape(Paper drawingPaper, Shape shape, ShapeEditor shapeEditor, double offsetX, double offsetY) {
        super(drawingPaper, shape);
        this.shapeEditor = shapeEditor;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @Override
    public void execute() {
       shapeEditor.dragShape(this.offsetX, this.offsetY);
        
    }

    @Override
    public void undo() {
        
    }
    
}
