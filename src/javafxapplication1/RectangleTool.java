/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cassd
 */
public class RectangleTool extends ToolState{
    
    private Rectangle currentRectangle;
    private Color rectangleStrokeColor;
    private Color rectangleFillColor;
    private Pane drawingPane;
    
    private double startX;
    private double startY;

    public RectangleTool(Pane drawingPane, Color rectangleStrokeColor, Color rectangleFillColor) {
        this.drawingPane = drawingPane;
        
        if (rectangleStrokeColor != null){
            this.rectangleStrokeColor = rectangleStrokeColor;
        }
        else{
            this.rectangleStrokeColor = Color.BLACK; // Ricorda di aggiustare il selettore mettendo nero di default
        }
        
        if (rectangleFillColor != null){
            this.rectangleFillColor = rectangleFillColor;
        }
        else{
            this.rectangleFillColor = Color.BLACK; 
        }
         
        
        
    }
    
    public void setRectangleStrokeColor(Color color) {
        this.rectangleStrokeColor = color;
    }
    
    public void setRectangleFillColor(Color color) {
        this.rectangleFillColor = color;
    }
    
    @Override
    public void onMousePressed(MouseEvent event) {
        currentRectangle = new Rectangle();
        this.startX=event.getX();
        this.startY=event.getY();
        currentRectangle.setX(this.startX);
        currentRectangle.setY(this.startY);
        currentRectangle.setStroke(rectangleStrokeColor);
        currentRectangle.setFill(rectangleFillColor);
        currentRectangle.setStrokeWidth(3);
        
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentRectangle != null) {
            double actualX = event.getX();
            double actualY = event.getY();
            double width = Math.abs(this.startX-actualX);
            double height = Math.abs(this.startY-actualY);
            this.currentRectangle.setWidth(width);
            this.currentRectangle.setHeight(height);
            currentRectangle.setX(Math.min(this.startX, actualX));
            currentRectangle.setY(Math.min(this.startY, actualY));
            
            drawingPane.getChildren().add(currentRectangle);
            System.out.println("Rettangolo Disegnato");
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        if (currentRectangle != null) {
            
        }
    }
    
    
    @Override
    public void setStrokeColor(Color color){
        this.rectangleStrokeColor = color;
    }
    
    
    
    
    
    
}
