package javafxapplication1;

import javafx.scene.shape.Shape;

public class CutShapeCommand extends Command {
    private Clipboard clipboard;

    public CutShapeCommand(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        assert shape != null : "CutShapeCommand: shape non deve essere null!";
        clipboard.copy(shape);
        drawingPaper.removeOnPaper(shape);
        System.out.println("Figura tagliata: " + shape.getId());
    }

    @Override
    public void undo() {
        assert shape != null : "CutShapeCommand undo: shape non deve essere null!";
        drawingPaper.addOnPaper(shape);
        System.out.println("Figura ripristinata dopo taglio.");
    }
}
