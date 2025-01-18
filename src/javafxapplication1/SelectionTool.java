/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author cassd
 */
public class SelectionTool extends ToolState{
    
    private Paper paper;
    private ShapeEditor shapeEditor;
    //private double startX;
    //private double startY;
   
    public SelectionTool(Paper paper){
        this.paper = paper;
        
    }
    
   
    @Override
    public void onMousePressed(MouseEvent event) {
        //this.startX = event.getX();
        //this.startY = event.getY();
        double startX = event.getX();
        double startY = event.getY();
       
        ObservableList lista = paper.getAnchorPanePaper().getChildren();
        System.out.println("OGGETTI SUL FOGLIO " + lista.size());
        Iterator iter = lista.iterator();
        while (iter.hasNext()){
            Shape s = (Shape) iter.next();
            if (s.contains(event.getX(),event.getY())){
                //this.shapeselected.setStroke(Color.RED);
                System.out.println("FIGURA SELEZIONATA " + s.getStroke());
                if (s instanceof Ellipse){
                    
                }
                if (s instanceof Rectangle){
                    
                }
                if (s instanceof Line){
                    
                }
                
                this.shapeEditor = new ShapeEditor(s,paper, startX, startY);
                
       
            }
          
            
        }
        
        
    }
    
    /*
    @Override
    public void onMouseDragged(MouseEvent event) {
     
        double dragX = event.getX();
        double dragY = event.getY();
        double offsetX = dragX - this.startX;
        double offsetY = dragY - this.startY;
        //forma = this.createSameTypeShape(this.shapeselected);
        //System.out.println("CLASSE " + this.shapeselected.getClass().getSimpleName());   
        if (this.shapeselected instanceof Ellipse){
            Ellipse ellipse = (Ellipse) this.shapeselected;
            ellipse.setCenterX(dragX);
            ellipse.setCenterY(dragY);
        }
        
        if (this.shapeselected instanceof Rectangle){
            Rectangle rectangle = (Rectangle) this.shapeselected;
            rectangle.setX(rectangle.getX() + offsetX);
            rectangle.setY(rectangle.getY() + offsetY);
         
        }
        
        if (this.shapeselected instanceof Line) {
            Line line = (Line) this.shapeselected;
            line.setStartX(line.getStartX() + offsetX);
            line.setStartY(line.getStartY() + offsetY);
            line.setEndX(line.getEndX() + offsetX);
            line.setEndY(line.getEndY() + offsetY);
            
            
            
        }   
        this.startX = dragX;
        this.startY = dragY;
    }
    */
    
    @Override
    public void onMouseDragged(MouseEvent event) {
        double dragX = event.getX();
        double dragY = event.getY();
        double offsetX = dragX - shapeEditor.getStartX();
        double offsetY = dragY - shapeEditor.getStartY();
        Invoker invoker = Invoker.getInvoker();
        invoker.executeCommand(new DragShape(paper,this.shapeEditor.getShape(),this.shapeEditor, offsetX, offsetY));
        this.shapeEditor.setStartX(dragX);
        this.shapeEditor.setStartY(dragY);
    }
    
    

    
    @Override
    public void onMouseReleased(MouseEvent event) {
        this.shapeEditor.setShape(null);
    }
    
}
