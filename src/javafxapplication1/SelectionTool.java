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
    private Shape shapeselected;
    private double startX;
    private double startY;
   
    public SelectionTool(Paper paper, Shape shape){
        this.paper = paper;
        this.shapeselected = shape;
        
    }
    
   
    @Override
    public void onMousePressed(MouseEvent event) {
        this.startX = event.getX();
        this.startY = event.getY();
        
       
        ObservableList lista = paper.getAnchorPanePaper().getChildren();
        System.out.println("OGGETTI SUL FOGLIO " + lista.size());
        Iterator iter = lista.iterator();
        while (iter.hasNext()){
            Shape s = (Shape) iter.next();
            if (s.contains(event.getX(),event.getY())){
                this.shapeselected = s;
                this.shapeselected.setStroke(Color.RED);
                System.out.println("FIGURA SELEZIONATA IN ROSSO" + s);
       
            }
          
            
        }
        
        
    }

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
            this.startX = dragX;
            this.startY = dragY;
     
        }
        
        if (this.shapeselected instanceof Line) {
            Line line = (Line) this.shapeselected;
               
        }   
        
    }

    
    
    
    
    public <T extends Shape> T createSameTypeShape(T s) {
        try {
            // Ottieni la classe specifica della forma (es. Line, Rectangle)
            Class<?> shapeClass = s.getClass();

            // Crea una nuova istanza del tipo specifico
            @SuppressWarnings("unchecked") // Sopprime il warning del cast
            T newShape = (T) shapeClass.getDeclaredConstructor().newInstance();

            return newShape;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore nella creazione della nuova forma", e);
        }
}
    
    
    
    
    
    
    
    @Override
    public void onMouseReleased(MouseEvent event) {
        this.shapeselected = null;
    }
    
}
