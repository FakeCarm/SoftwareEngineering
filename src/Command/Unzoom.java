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
 * @author cassd
 */
public class Unzoom extends Command{
    
    private Paper drawingPaper;
    
    public Unzoom(Paper drawingPaper) {
        this.drawingPaper = drawingPaper;
    }

    @Override
    public void execute() {
        this.drawingPaper.unzoomOnPaper();
    }

    @Override
    public void undo() {
        this.drawingPaper.zoomOnPaper();
    }

    @Override
    public void redo() {
        execute();
    }
    
}
