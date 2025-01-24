package Command;

import Command.Command;
import javafx.scene.shape.Shape;
import javafxapplication1.Clipboard;
import javafxapplication1.Paper;

public class CopyShapeCommand extends Command {
    private Clipboard clipboard;
    private Shape previouslyCopiedShape;

    public CopyShapeCommand(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        assert super.shape != null : "CopyShapeCommand: shape non deve essere null!";
        previouslyCopiedShape = clipboard.getCopiedShape();
        clipboard.copy(super.shape);
        System.out.println("Figura copiata: " + shape.getStrokeWidth());
    }

    @Override
    public void undo() {
        clipboard.copy(previouslyCopiedShape);
        System.out.println("Undo della copia eseguito.");
    }

    @Override
    public void redo() {
        clipboard.copy(super.shape);
        System.out.println("Redo della copia eseguito: " + shape.getStrokeWidth());
    }
}