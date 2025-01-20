/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 *
 * @author cassd
 */
public class LineShapeEditor extends ShapeEditor{
    private Line line;

    public LineShapeEditor(Shape shape, Paper drawingPaper,Pane paneEditor, double startX, double startY) {
        super(shape, drawingPaper,paneEditor, startX, startY);
        this.line = (Line) shape;
    }

    @Override
    public void dragShape(double offsetX, double offsetY) {
        line.setStartX(line.getStartX() + offsetX);
        line.setStartY(line.getStartY() + offsetY);
        line.setEndX(line.getEndX() + offsetX);
        line.setEndY(line.getEndY() + offsetY);
    }

    
    
   
    
    
    
}
