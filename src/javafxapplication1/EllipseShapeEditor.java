/*
 
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.*/
package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
@author vinjs
*/
public class EllipseShapeEditor extends ShapeEditor{

    private Ellipse ellipse;

    public EllipseShapeEditor(Shape shape, Paper drawingPaper,Pane paneEditor, double startX, double startY) {
        super(shape, drawingPaper,paneEditor, startX, startY);
        this.ellipse = (Ellipse) shape;
    }

    @Override
    public void dragShape(double offsetX, double offsetY){
        ellipse.setCenterX(ellipse.getCenterX() + offsetX);
        ellipse.setCenterY(ellipse.getCenterY() + offsetY);
    }

}