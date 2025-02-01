/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Command;
import javafx.scene.shape.Shape;
import javafxapplication1.Paper;

/**
 * Classe che racchiude il comando della rotazione che consente di ruotare di un certo angolo la figura selezionata
 * @author vinjs
 */
public class RotateShape extends Command {
    
    private double oldAngle;
    private double newAngle;
    private Shape shape;

    public RotateShape(Shape shape, double oldAngle, double newAngle) {
        this.shape = shape;
        this.oldAngle = oldAngle;
        this.newAngle = newAngle;
    }

    @Override
    public void execute() {
        if (shape != null) {
            shape.setRotate(newAngle);
        }
    }

    @Override
    public void undo() {
        if (shape != null) {
            shape.setRotate(oldAngle);
        }
    }

    @Override
    public void redo() {
        execute();
    }
    
}
