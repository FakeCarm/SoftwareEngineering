package Command;

import Command.Command;
import javafx.scene.shape.Shape;
import javafxapplication1.Clipboard;
import javafxapplication1.Paper;

public class CopyShape extends Command {
    private Clipboard clipboard;
    private Shape previouslyCopiedShape;
    private Shape shape;

    public CopyShape(Paper drawingPaper, Shape shape) {
        this.shape = shape;
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        assert shape != null : "CopyShapeCommand: shape non deve essere null!";
        previouslyCopiedShape = clipboard.getCopiedShape();
        clipboard.copy(this.shape);
        System.out.println("Figura copiata: " + shape.getStrokeWidth());
    }

    @Override
    public void undo() {
        clipboard.copy(previouslyCopiedShape);
        System.out.println("Undo della copia eseguito.");
    }

    @Override
    public void redo() {
        clipboard.copy(this.shape);
        System.out.println("Redo della copia eseguito: " + shape.getStrokeWidth());
    }
}