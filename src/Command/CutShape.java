package Command;

import Command.Command;
import javafx.scene.shape.Shape;
import javafxapplication1.Clipboard;
import javafxapplication1.Paper;

public class CutShape extends Command {
    private Clipboard clipboard;
    private boolean wasShapeInClipboard;

    public CutShape(Paper drawingPaper, Shape shape) {
        super(drawingPaper, shape);
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        assert shape != null : "CutShapeCommand: shape non deve essere null!";
         wasShapeInClipboard = (clipboard.getCopiedShape() == shape);
        clipboard.copy(shape);
        drawingPaper.removeOnPaper(shape);
        System.out.println("Figura tagliata: " + shape.getId());
    }

    @Override
    public void undo() {
        assert shape != null : "CutShapeCommand undo: shape non deve essere null!";
        drawingPaper.addOnPaper(shape);
        if (!wasShapeInClipboard) {
           clipboard.clear();
        }
        System.out.println("Figura ripristinata dopo taglio.");
    }

    @Override
    public void redo() {
        assert shape != null : "CutShapeCommand redo: shape non deve essere null!";
        
        clipboard.copy(shape);
        drawingPaper.removeOnPaper(shape);

        System.out.println("Redo del taglio eseguito: figura rimossa di nuovo.");
    }
}