/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import Command.AddShape;
import Command.Invoker;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author cassd
 */
public class PolygonTool extends SelectedShapeTool{
    
    private Polygon polygon;
    private Paper drawingPaper;
    private int count = 0;
   
   
    /**
     * Costruttore che si occupa di costruire la forma vuota quando si seleziona lo strumento poligono
     * @param drawingPaper
     * @param strokeColor
     * @param fillColor 
     */
    public PolygonTool(Paper drawingPaper,Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        this.polygon = null;
        this.drawingPaper = drawingPaper;
         
    }
    
    /**
     * Setta il primo punto del poligono. Se esiste gia un punto e viene premuto il tasto destro del mouse la figura viene deselezionata
     * @param event 
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        if(this.polygon != null){
                this.polygon.getPoints().addAll(event.getX(),event.getY());       
        }
        else{
            this.polygon = new Polygon(event.getX(), event.getY());
            this.polygon.setId("Poly"+(count++));
            this.polygon.setStroke(this.getStrokeColor());
            this.polygon.setFill(this.getFillColor());
            this.polygon.setStrokeWidth(5);
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new AddShape(this.drawingPaper,this.polygon));    
        }
    }
    
    /**
     * Si occupa di aggiornare l'ultimo punto inserito in modo da mostrare come si evolve la figura.
     * @param event 
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if(this.polygon!=null){
            double dragX,dragY;
            dragX = event.getX();
            dragY = event.getY();
            int size = this.polygon.getPoints().size();
            if (size >= 2) {
                // Aggiorna le ultime due coordinate con la posizione del mouse
                this.polygon.getPoints().set(size - 2, dragX);
                this.polygon.getPoints().set(size - 1, dragY);
            }
        }
        
        
        
    }

    @Override
    public void onMouseReleased(MouseEvent event) {  }
    
//    public Polygon getPolygon(){
//        return this.polygon;
//    }
    
//    /**
//     * Applica l'ombreggiatura alla forma.
//     */
//    private void applyDropShadow() {
//        DropShadow dropShadow = new DropShadow();
//        dropShadow.setRadius(5.0);
//        dropShadow.setOffsetX(3.0);
//        dropShadow.setOffsetY(3.0);
//        dropShadow.setColor(javafx.scene.paint.Color.GREY);
//        this.polygon.setEffect(dropShadow);
//    }
}
