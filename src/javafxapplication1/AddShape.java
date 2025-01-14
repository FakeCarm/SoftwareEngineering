/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

/**
 *
 * @author cassd
 */
public class AddShape extends Command{

    public AddShape(AnchorPane anchorPanePaper, Shape shape) {
        super(anchorPanePaper, shape);
    }

    
   
    @Override
    public void execute(){
        anchorPanePaper.getChildren().add(shape);
    }
    
    @Override
    public void undo(){}
    
}
