package javafxapplication1;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PasteShapeCommand extends Command {
    private Clipboard clipboard;
    private Shape pastedShape;

    public PasteShapeCommand(Paper drawingPaper) {
        super(drawingPaper, null);
        this.clipboard = Clipboard.getInstance();
    }

    @Override
    public void execute() {
        Shape copiedShape = clipboard.getCopiedShape();
        if (copiedShape != null) {
            pastedShape = duplicateShape(copiedShape); // Duplica la figura
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

    private Shape duplicateShape(Shape original) {
        if (original instanceof Rectangle) {
            Rectangle rect = (Rectangle) original;
            Rectangle copy = new Rectangle(rect.getX() + 10, rect.getY() + 10, rect.getWidth(), rect.getHeight());
            copy.setFill(rect.getFill());
            copy.setStroke(rect.getStroke());
            return copy;
        } else if (original instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) original;
            Ellipse copy = new Ellipse(ellipse.getCenterX() + 10, ellipse.getCenterY() + 10, ellipse.getRadiusX(), ellipse.getRadiusY());
            copy.setFill(ellipse.getFill());
            copy.setStroke(ellipse.getStroke());
            return copy;
        } else if (original instanceof Line) {
            Line line = (Line) original;
            Line copy = new Line(line.getStartX() + 10, line.getStartY() + 10, line.getEndX() + 10, line.getEndY() + 10);
            copy.setStroke(line.getStroke());
            copy.setStrokeWidth(line.getStrokeWidth());
            return copy;
        }
        return null;
    }
}
