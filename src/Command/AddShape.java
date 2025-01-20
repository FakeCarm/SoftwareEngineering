/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 *
 * @author cassd
 */
public class AddShape extends Command{

    public AddShape(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
    }

    
   
    @Override
    public void execute(){
        assert super.drawingPaper != null : "AddShaoe: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        super.drawingPaper.addOnPaper(super.shape);
        
        
        
    }
    
    @Override
    public void undo(){}
    
}
