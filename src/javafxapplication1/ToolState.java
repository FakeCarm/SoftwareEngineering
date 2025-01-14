package javafxapplication1;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.scene.shape.Shape;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cassd
 */



/*
The State interface declares the state-specific methods.
These methods should make sense for all concrete states because you don’t want some
of your states to have useless methods that will never be called
*/
public abstract class ToolState {
    
    @FXML
    public AnchorPane anchorPaneBar;
 
    public ToolState(){
        
    }
    
    
    public abstract void onMousePressed(MouseEvent event);
    
    public abstract void onMouseDragged(MouseEvent event);
    
    public abstract void onMouseReleased(MouseEvent event);
    
    //public abstract void setStrokeColor(Color color);
    
    
    
  
    
    
}
