/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import Command.Invoker;
import Command.DragShape;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private Pane paneEditor;
    //private Shape selectedShape;
    //private double startX;
    //private double startY;
   
    public SelectionTool(Paper paper, Pane paneEditor){
        this.paper = paper;
        this.shapeEditor = null;
        this.paneEditor = paneEditor;
    }
    
   
    @Override
    public void onMousePressed(MouseEvent event) {
        //this.startX = event.getX();
        //this.startY = event.getY();
        double startX = event.getX();
        double startY = event.getY();
        boolean condition = false;
        ObservableList lista = paper.getAnchorPanePaper().getChildren();
        //System.out.println("OGGETTI SUL FOGLIO " + lista.size());
        
        Iterator iter = lista.iterator();
        while (iter.hasNext()){
            Object obj = iter.next();
            if (obj instanceof Shape){
                Shape s = (Shape) obj;
                condition = s.contains(event.getX(),event.getY());
            
                if (condition){
                    //this.shapeselected.setStroke(Color.RED);
                    System.out.println("FIGURA SELEZIONATA " + s.getStroke());

                    if (this.shapeEditor != null){
                        if(this.shapeEditor.getShape() != null){
                            this.shapeEditor.getShape().setEffect(null);
                            this.shapeEditor.setShape(null);
                            this.shapeEditor = null;
                        }
                    }

                    if (s instanceof Ellipse){
                        this.shapeEditor = new EllipseShapeEditor(s,paper,this.paneEditor,startX,startY);    
                    }
                    if (s instanceof Rectangle){
                        this.shapeEditor = new RectangleShapeEditor(s,paper,this.paneEditor,startX,startY);
                    }
                    if (s instanceof Line){
                        this.shapeEditor = new LineShapeEditor(s,paper,this.paneEditor,startX,startY);
                    }

                    //this.shapeEditor = new ShapeEditor(s,paper, startX, startY);
                           // Creazione dell'effetto Glow

                    if (this.shapeEditor != null){
                        //System.out.println("EFFETTO");
                        DropShadow dropShadow = new DropShadow();
                        dropShadow.setRadius(5.0);
                        dropShadow.setOffsetX(3.0);
                        dropShadow.setOffsetY(3.0);
                        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
                        this.shapeEditor.getShape().setEffect(dropShadow);
                        return;
                    }   
                }  
            }
                
        }
        if (this.shapeEditor != null){
            if(this.shapeEditor.getShape() != null){
                    this.shapeEditor.getShape().setEffect(null);
                    this.shapeEditor.setShape(null);
                    this.shapeEditor = null;
                }
        }
        
    }
 
    @Override
    public void onMouseDragged(MouseEvent event) {
        
        if (this.shapeEditor != null && this.shapeEditor.getShape() != null){
            //System.out.println("DRAGG");
            double dragX = event.getX();
            double dragY = event.getY();
            double offsetX = dragX - shapeEditor.getStartX();
            double offsetY = dragY - shapeEditor.getStartY();
            Invoker invoker = Invoker.getInvoker();
            if (invoker != null){
                invoker.executeCommand(new DragShape(paper,this.shapeEditor.getShape(),this.shapeEditor, offsetX, offsetY));
                this.shapeEditor.setStartX(dragX);
                this.shapeEditor.setStartY(dragY);
            }
                
            
        }
        
    }
    
    

    
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (this.shapeEditor != null){
            this.paneEditor.setVisible(true);
             //shapeEditor.getShape().setEffect(null);
           //this.shapeEditor.setShape(null);
        }else{
            this.paneEditor.setVisible(false);
        }
        
    }
    
    
    public ShapeEditor getEditor(){
        return this.shapeEditor;
    }
    
    public Paper getPaper() {
        return paper;
    }

    public Pane getPaneEditor() {
        return paneEditor;
    }
}
