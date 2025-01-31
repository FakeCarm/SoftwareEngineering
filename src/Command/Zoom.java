/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Classe che rappresenta il comando di Zoom.
 * @author cassd
 */
public class Zoom extends Command{
    
    private Paper drawingPaper;
    
    public Zoom(Paper drawingPaper) {
        this.drawingPaper = drawingPaper;
    }
    
    /**
     * metodo che richiama il comando.
     */
    @Override
    public void execute() {
        this.drawingPaper.zoomOnPaper();
    }

    @Override
    public void undo() {
        this.drawingPaper.unzoomOnPaper();
    }

    @Override
    public void redo() {
        execute();
    }
    
}
