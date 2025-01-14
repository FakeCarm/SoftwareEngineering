/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
/**
 *
 * @author cassd
 */
public abstract class Command {
    
    @FXML
    public AnchorPane anchorPanePaper;
    
    public Shape shape;

    public Command(AnchorPane anchorPanePaper, Shape shape) {
        this.anchorPanePaper = anchorPanePaper;
        this.shape = shape;
    }
    
 
    public abstract void execute();
    
    public abstract void undo();
    
   
}
