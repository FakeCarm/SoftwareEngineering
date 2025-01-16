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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 *
 * @author cassd
 */
public class SelectionTool extends ToolState{
    
    private Paper paper;
    private Shape shapeselected;
    
    public SelectionTool(Paper paper, Shape shape){
        this.paper = paper;
        this.shapeselected = shape;
        
    }
    
   
    @Override
    public void onMousePressed(MouseEvent event) {
        ObservableList lista = paper.getAnchorPanePaper().getChildren();
        System.out.println("OGGETTI SUL FOGLIO " + lista.size());
        Iterator iter = lista.iterator();
        while (iter.hasNext()){
            Shape s = (Shape) iter.next();
            if (s.contains(event.getX(),event.getY())){
                this.shapeselected = s;
                System.out.println("FIGURA SELEZIONATA " + s);
            }
            
        }
        
        
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (this.shapeselected != null){
            this.shapeselected.setTranslateX(event.getX());
            this.shapeselected.setTranslateY(event.getY()); 
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        this.shapeselected = null;
    }
    
}
