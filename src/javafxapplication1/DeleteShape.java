/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import javafx.scene.shape.Shape;

/**
 *
 * @author cassd
 */
public class DeleteShape extends Command{

    public DeleteShape(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
    }

    @Override
    public void execute() {
        assert super.drawingPaper != null : "AddShaoe: drawingPaper non deve essere null!";
        assert super.shape != null : "AddShape: shape non deve essere null";
        super.drawingPaper.removeOnPaper(super.shape);
    }

    @Override
    public void undo() {
        
    }
    
}
