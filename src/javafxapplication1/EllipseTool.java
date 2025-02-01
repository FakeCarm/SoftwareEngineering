package javafxapplication1;

import Command.AddShape;
import Command.Invoker;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Strumento per creare e gestire l'interazione con ellissi disegnate su un'area di lavoro.
 */
public class EllipseTool extends SelectedShapeTool {

    private Ellipse currentEllipse;
    private Paper drawingPaper;
    private int count = 0;
    private double startX;
    private double startY;

    /**
     * Costruttore che inizializza lo strumento ellisse.
     * @param drawingPaper
     * @param strokeColor     il colore del bordo dell'ellisse.
     * @param fillColor       il colore di riempimento dell'ellisse.
     */
    public EllipseTool(Paper drawingPaper, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        this.drawingPaper = drawingPaper;
    }

    

    /**
     * Gestisce l'evento di pressione del mouse, inizializzando il disegno di una nuova ellisse.
     *
     * @param event l'evento di pressione del mouse.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        currentEllipse = new Ellipse();
        currentEllipse.setId("ellipse" + (count++));

        startX = event.getX();
        startY = event.getY();
        currentEllipse.setCenterX(startX);
        currentEllipse.setCenterY(startY);
        currentEllipse.setRadiusX(0);
        currentEllipse.setRadiusY(0);

        currentEllipse.setStroke(super.getStrokeColor());
        currentEllipse.setFill(super.getFillColor());
        currentEllipse.setStrokeWidth(5);

        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(drawingPaper, currentEllipse));
        }
    }

    /**
     * Gestisce l'evento di trascinamento del mouse, aggiornando la dimensione dell'ellisse.
     *
     * @param event l'evento di trascinamento del mouse.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentEllipse != null) {
            double currentX = event.getX();
            double currentY = event.getY();

            double radiusX = Math.abs(startX - currentX) / 2;
            double radiusY = Math.abs(startY - currentY) / 2;

            currentEllipse.setRadiusX(radiusX);
            currentEllipse.setRadiusY(radiusY);
            currentEllipse.setCenterX((startX + currentX) / 2);
            currentEllipse.setCenterY((startY + currentY) / 2);
        }
    }

    /**
     * Gestisce l'evento di rilascio del mouse, completando il disegno dell'ellisse.
     *
     * @param event l'evento di rilascio del mouse.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (currentEllipse != null) {
            double finalX = event.getX();
            double finalY = event.getY();

            double radiusX = Math.abs(startX - finalX) / 2;
            double radiusY = Math.abs(startY - finalY) / 2;

            currentEllipse.setRadiusX(radiusX);
            currentEllipse.setRadiusY(radiusY);
            currentEllipse.setCenterX((startX + finalX) / 2);
            currentEllipse.setCenterY((startY + finalY) / 2);

            currentEllipse = null;
        }
    }
}
