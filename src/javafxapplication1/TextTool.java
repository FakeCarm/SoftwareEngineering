/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import Command.AddShape;
import Command.Invoker;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *  Classe che rappresento la selezione del tool per inserire una casella di testo
 * @author cassd
 */
public class TextTool extends SelectedShapeTool{
   
    private Text text;
    private Paper drawingPaper;
    private double startX;
    private double startY;
    private int count = 0;
    
    
    public TextTool(Paper paper, Color strokeColor, Color fillColor){
        super(strokeColor,fillColor);
        this.drawingPaper = paper;
    }
    
    /**
     * Viene inserita una casella di testo alla pressione sul foglio da disegno
     * @param event 
     */
    @Override
    public void onMousePressed(MouseEvent event) {
    
        this.startX = event.getX();
        this.startY = event.getY();
        
        Text textArea = new Text("Inserisci testo");
        textArea.setId("Text"+(count++));
        textArea.setFont(Font.font("Arial", 20));
        textArea.setFill(super.getFillColor());
        textArea.setX(startX);
        textArea.setY(startY);
        this.text = textArea;
        this.text.applyCss();
        
    
        Invoker invoker = Invoker.getInvoker();
        if(invoker != null){
            invoker.executeCommand(new AddShape(drawingPaper,this.text));
        }
             
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
 
    }
    
    /**
     * VMetodo che consente di settare il testo nella casella
     * @param c 
     */
    public void setText(String c){
        if(this.text!=null){
           if (c != null){
            this.text.setText(c); 
            } 
        }
    }
    
    /**
     * Metodo che ritorna il testo nella casella
     * @return String
     */
    public String getText(){
        if(this.text!=null){
            if("Inserisci testo".equals(this.text.getText())){ // se è presente il testo di default devo ritornare qualcosa di vuoto.
                return "";
            }
           return this.text.getText();
        }
        return null;
    }
    
    /**
     * Metodo che ritorna la casella di testo
     * @return Text
    */
    public Text getTextShape(){
        if (this.text!=null){
           return this.text; 
        }
        return null;
    }
}
