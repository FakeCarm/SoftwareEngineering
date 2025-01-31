/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;

import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafxapplication1.Paper;
import javafxapplication1.ShapeEditor;

/**
 *
 * @author vinjs
 */
public class MirrorShape extends Command{
    
    private boolean isVertical;
    private double previousScaleX;
    private double previousScaleY;
    private Shape shape;

    public MirrorShape(Shape shape, boolean isVertical) {
        this.shape = shape;
        this.isVertical = isVertical;
    }

    @Override
    public void execute() {
    
        if (shape == null) {
            System.out.println("Errore: nessuna forma selezionata per la specchiatura.");
            return;
        }
        
        previousScaleX = shape.getScaleX();
        previousScaleY = shape.getScaleY();
        if (isVertical) {
            shape.setScaleY(-previousScaleY);
        } else {
            shape.setScaleX(-previousScaleX);
        }
    }

    @Override
    public void undo() {
        if (shape == null) {
            System.out.println("Errore: impossibile annullare la specchiatura, nessuna forma trovata.");
            return;
        }
        shape.setScaleX(previousScaleX);
        shape.setScaleY(previousScaleY);
    }

    @Override
    public void redo() {
        execute();
    }
    
}
