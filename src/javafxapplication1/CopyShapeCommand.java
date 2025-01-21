package javafxapplication1;

import javafx.scene.shape.Shape;

public class CopyShapeCommand extends Command {
    private Clipboard clipboard;

    public CopyShapeCommand(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        assert shape != null : "CopyShapeCommand: shape non deve essere null!";
        clipboard.copy(shape);
        System.out.println("Figura copiata: " + shape.getId());
    }

    @Override
    public void undo() {
        // Non necessario per la copia
    }
}
