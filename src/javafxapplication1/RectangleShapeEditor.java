/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author cassd
 */
public class RectangleShapeEditor extends ShapeEditor{
    
    private Rectangle rectangle;
    
    public RectangleShapeEditor(Shape shape, Paper drawingPaper, double startX, double startY) {
        super(shape, drawingPaper, startX, startY);
        this.rectangle = (Rectangle) shape;
    }
    
}
