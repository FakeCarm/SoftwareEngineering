/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 *
 * @author vinjs
 */
public class BringToFront extends Command{

    public BringToFront(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
    }

    @Override
    public void execute() {
        shape.toFront();
    }

    @Override
    public void undo() {
        shape.toBack();
    }

    @Override
    public void redo() {
        execute();
    }
    
}
