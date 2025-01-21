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
    private Paper anchorPanePaper;
    public int count = 0;
    private double startX;
    private double startY;

    /**
     * Costruttore che inizializza lo strumento ellisse.
     *
     * @param anchorPanePaper l'area di lavoro su cui disegnare.
     * @param toolBar         la barra degli strumenti associata.
     * @param strokeColor     il colore del bordo dell'ellisse.
     * @param fillColor       il colore di riempimento dell'ellisse.
     */
    public EllipseTool(Paper anchorPanePaper, ToolBar toolBar, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        super.toolBar = toolBar;
        this.anchorPanePaper = anchorPanePaper;
    }

    /**
     * Restituisce l'ellisse attualmente in fase di disegno.
     *
     * @return l'ellisse corrente o null se non c'è un disegno attivo.
     */
    public Ellipse getCurrentEllipse() {
        return currentEllipse;
    }

    /**
     * Imposta l'ellisse attualmente in fase di disegno.
     *
     * @param currentEllipse l'ellisse corrente.
     */
    public void setCurrentEllipse(Ellipse currentEllipse) {
        this.currentEllipse = currentEllipse;
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

        currentEllipse.setStroke(strokeColor.get());
        currentEllipse.setFill(fillColor.get());
        currentEllipse.setStrokeWidth(5);

        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(anchorPanePaper, currentEllipse));
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
