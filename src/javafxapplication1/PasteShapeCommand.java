package javafxapplication1;

import Command.Command;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PasteShapeCommand extends Command {
    private Clipboard clipboard;
    private Shape pastedShape;
    private double pasteX;
    private double pasteY;

    public PasteShapeCommand(Paper drawingPaper, double pasteX, double pasteY) {
        super(drawingPaper, null);
        this.clipboard = Clipboard.getInstance();
        this.pasteX = pasteX;
        this.pasteY = pasteY;
    }

    @Override
    public void execute() {
        Shape copiedShape = clipboard.getCopiedShape();
        if (copiedShape != null) {
            pastedShape = duplicateShape(copiedShape, pasteX, pasteY); // Duplica la figura e aggiorna la posizione
            drawingPaper.addOnPaper(pastedShape);
            System.out.println("Figura incollata: " + pastedShape.getId());
        } else {
            System.out.println("Clipboard vuota.");
        }
    }

    @Override
    public void undo() {
        if (pastedShape != null) {
            drawingPaper.removeOnPaper(pastedShape);
            System.out.println("Figura incollata rimossa.");
        }
    }

    private Shape duplicateShape(Shape original, double x, double y) {
        if (original instanceof Rectangle) {
            Rectangle rect = (Rectangle) original;
            Rectangle copy = new Rectangle(x, y, rect.getWidth(), rect.getHeight());
            copy.setFill(rect.getFill());
            copy.setStroke(rect.getStroke());
            copy.setStrokeWidth(rect.getStrokeWidth());
            return copy;
        } else if (original instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) original;
            Ellipse copy = new Ellipse(x, y, ellipse.getRadiusX(), ellipse.getRadiusY());
            copy.setFill(ellipse.getFill());
            copy.setStroke(ellipse.getStroke());
            copy.setStrokeWidth(ellipse.getStrokeWidth());
            return copy;
        } else if (original instanceof Line) {
            Line line = (Line) original;
            double offsetX = x - line.getStartX();
            double offsetY = y - line.getStartY();
            Line copy = new Line(line.getStartX() + offsetX, line.getStartY() + offsetY, 
                                 line.getEndX() + offsetX, line.getEndY() + offsetY);
            copy.setStroke(line.getStroke());
            copy.setStrokeWidth(line.getStrokeWidth());
            return copy;
        }
        return null;
    }
}
