/*
 
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.*/
package javafxapplication1;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
@author vinjs
*/
public class EllipseShapeEditor extends ShapeEditor{

    private Ellipse ellipse;

    public EllipseShapeEditor(Shape shape, Paper drawingPaper, double startX, double startY) {
        super(shape, drawingPaper, startX, startY);
        this.ellipse = (Ellipse) shape;
    }

    @Override
    public void dragShape(double offsetX, double offsetY){
        ellipse.setCenterX(ellipse.getCenterX() + offsetX);
        ellipse.setCenterY(ellipse.getCenterY() + offsetY);
    }

}