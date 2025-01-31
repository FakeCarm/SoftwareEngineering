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
public class SendToBack extends Command {
    private Shape shape;
    public SendToBack(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void execute() {
        shape.toBack();
    }

    @Override
    public void undo() {
        shape.toFront();
    }

    @Override
    public void redo() {
        execute();
    }
    
}
