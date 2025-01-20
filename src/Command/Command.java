/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;
/**
 *
 * @author cassd
 */
public abstract class Command {
    
    
    public Paper drawingPaper;
    public Shape shape;

    public Command(Paper drawingPaper, Shape shape){
        this.drawingPaper = drawingPaper;
        this.shape = shape;
    }
    
 
    public abstract void execute();
    
    public abstract void undo();
    
   
}
