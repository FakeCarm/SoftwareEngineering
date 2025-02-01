package javafxapplication1;

import Command.AddShape;
import Command.Invoker;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Gestisce la creazione e la modifica di rettangoli all'interno del foglio.
 */
public class RectangleTool extends SelectedShapeTool {

    private Rectangle currentRectangle;
    private Paper drawingPaper;
    private int count = 0;  // Conta per assegnare un ID univoco a ciascun rettangolo
    private double startX;
    private double startY;

    /**
     * Costruttore per inizializzare lo strumento rettangolo.
     *
     * @param drawingPaper
     * @param strokeColor il colore del bordo del rettangolo.
     * @param fillColor il colore di riempimento del rettangolo.
     */
    public RectangleTool(Paper drawingPaper, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        this.drawingPaper = drawingPaper;
    }

    /**
     * Gestisce il click iniziale per la creazione del rettangolo.
     *
     * @param event evento del mouse durante la pressione.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        currentRectangle = new Rectangle();
        currentRectangle.setId("rectangle" + (count++));
        this.startX = event.getX();
        this.startY = event.getY();
        currentRectangle.setX(this.startX);
        currentRectangle.setY(this.startY);

        // Colori dinamici
        currentRectangle.setStroke(super.getStrokeColor());
        currentRectangle.setFill(super.getFillColor());
        currentRectangle.setStrokeWidth(5);

        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(this.drawingPaper, currentRectangle));
        }
    }

    /**
     * Gestisce il trascinamento del mouse per ridimensionare il rettangolo.
     *
     * @param event evento del mouse durante il trascinamento.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentRectangle != null) {
            double actualX = event.getX();
            double actualY = event.getY();
            double width = Math.abs(this.startX - actualX);
            double height = Math.abs(this.startY - actualY);
            this.currentRectangle.setWidth(width);
            this.currentRectangle.setHeight(height);
            currentRectangle.setX(Math.min(this.startX, actualX));
            currentRectangle.setY(Math.min(this.startY, actualY));
        }
    }

    /**
     * Resetta il rettangolo corrente quando il mouse viene rilasciato.
     *
     * @param event evento del mouse durante il rilascio.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        currentRectangle = null;  // Resetta il rettangolo corrente
    }
}
