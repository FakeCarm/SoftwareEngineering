package javafxapplication1;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cassd
 */
public abstract class ShapeEditor {
    
    private Shape shape;
    private Paper drawingPaper;//Mi sa che non serve
    
    private Pane paneEditor;
    
    private double startX;
    private double startY;
    private double offsetX;
    private double offsetY;

    public ShapeEditor(Shape shape, Paper drawingPaper,Pane paneEditor, double startX, double startY) {
        this.shape = shape;
        this.drawingPaper = drawingPaper;
        this.startX = startX;
        this.startY = startY;
        this.paneEditor = paneEditor;
        this.paneEditor.setVisible(true);
        
    }
    
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }
    
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
    
    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }
    
    
    
    public void changeColor(){
       
    }
    
    public void changeSize(){
        
    }
    
    public abstract void dragShape(double offsetX, double offsetY);
    
    /*
    public void dragShape(double offsetX, double offsetY){
        if (this.shape instanceof Rectangle){
            Rectangle rectangle = (Rectangle) this.shape;
            rectangle.setX(rectangle.getX() + offsetX);
            rectangle.setY(rectangle.getY() + offsetY);
        }
        
    }
    */
    
    
    
    
    
    
}
