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
 *
 * @author cassd
 */
public class TextTool extends SelectedShapeTool{
    
    private StackPane textRect;
    private Rectangle area;
    private Text text;
    private Paper paper;
    private double startX;
    private double startY;
    private int count = 0;
    
    
    public TextTool(Paper paper, Color strokeColor, Color fillColor){
        super(strokeColor,fillColor);
        this.paper = paper;
    }
    
    @Override
    public void onMousePressed(MouseEvent event) {
        //Rectangle rectArea = new Rectangle();
        this.startX = event.getX();
        this.startY = event.getY();
        //rectArea.setX(this.startX);
        //rectArea.setY(this.startY);
        //rectArea.setFill(Color.TRANSPARENT);
        //rectArea.setStroke(Color.AQUAMARINE);
        //rectArea.setStrokeDashOffset(20);
        //rectArea.setStrokeLineCap(StrokeLineCap.ROUND);
        //rectArea.setId("Area"+count);
        //this.area = rectArea;
        
        Text textArea = new Text("Inserisci testo");
        textArea.setId("Text"+(count++));
        textArea.setFont(Font.font("Arial", 20));
        textArea.setFill(super.getFillColor());
        textArea.setX(startX);
        textArea.setY(startY);
        this.text = textArea;
        this.text.applyCss();
        
        //this.area.setWidth(this.text.getBoundsInLocal().getWidth() + 20);
        //this.area.setHeight(this.text.getBoundsInLocal().getHeight() + 20);

        
        //this.textRect = new StackPane();
        
        //this.textRect.setPrefSize(this.area.getWidth(), this.area.getHeight());
        //this.textRect.getChildren().addAll(area,text);
        
        
        
        Invoker invoker = Invoker.getInvoker();
        if(invoker != null){
            invoker.executeCommand(new AddShape(paper,this.text));
        }
        
        //this.textRect.setLayoutX(this.startX);
        //this.textRect.setLayoutY(this.startY);
        
        
        // TODO: BISOGNA CREARE IL COMANDO PER AGGIUNGERLO AL DISEGNOOOOOOOOOOOOOOO
        
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        /*
        if(this.text != null){
           //paper.getAnchorPanePaper().getChildren().remove(this.area);
           //this.area.setStroke(Color.TRANSPARENT);
           this.area = null;
           this.text = null;
           //this.textRect=null;
        }
        */
        
    }
    
    public void setText(String c){
        if(this.text!=null){
           if (c != null){
            this.text.setText(c); 
            } 
        }
    }
    
    public String getText(){
        if(this.text!=null){
            if("Inserisci testo".equals(this.text.getText())){ // se è presente il testo di default devo ritornare qualcosa di vuoto.
                return "";
            }
           return this.text.getText();
        }
        return null;
    }
}
