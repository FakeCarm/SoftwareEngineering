package javafxapplication1;

import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import Command.Invoker;
import Command.AddShape;

/**
 * Strumento per disegnare e gestire linee nell'area di lavoro.
 */
public class LineTool extends SelectedShapeTool {

    private Line currentLine;
    private Paper anchorPanePaper;
    public int count = 0;

    /**
     * Costruttore che inizializza lo strumento LineTool.
     *
     * @param anchorPanePaper l'area di lavoro su cui disegnare.
     * @param toolBar         la barra degli strumenti associata.
     * @param strokeColor     il colore del bordo della linea.
     * @param fillColor       il colore di riempimento della forma.
     */
    public LineTool(Paper anchorPanePaper, ToolBar toolBar, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        this.anchorPanePaper = anchorPanePaper;
        super.toolBar = toolBar;
    }

    /**
     * Gestisce l'evento di pressione del mouse per iniziare a disegnare una linea.
     *
     * @param event l'evento di pressione del mouse.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        currentLine = new Line();
        currentLine.setId("line" + (count++));
        currentLine.setStartX(event.getX());
        currentLine.setStartY(event.getY());
        currentLine.setEndX(event.getX());
        currentLine.setEndY(event.getY());

        // Colore dinamico della linea
        currentLine.setStroke(strokeColor.get());
        currentLine.setStrokeWidth(5);

        // Aggiunge la linea all'area di lavoro tramite il pattern Command
        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(this.anchorPanePaper, currentLine));
        }
    }

    /**
     * Gestisce l'evento di trascinamento del mouse per aggiornare la fine della linea.
     *
     * @param event l'evento di trascinamento del mouse.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentLine != null) {
            currentLine.setEndX(event.getX());
            currentLine.setEndY(event.getY());
        }
    }

    /**
     * Gestisce l'evento di rilascio del mouse per terminare il disegno della linea.
     *
     * @param event l'evento di rilascio del mouse.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {
        if (currentLine != null) {
            currentLine = null; // Reset della linea corrente
        }
    }

    /**
     * Restituisce la linea attualmente in fase di disegno.
     *
     * @return la linea corrente o null se non c'è un disegno attivo.
     */
    public Line getCurrentLine() {
        return currentLine;
    }

    /**
     * Imposta la linea attualmente in fase di disegno.
     *
     * @param currentLine la linea corrente da impostare.
     */
    public void setCurrentLine(Line currentLine) {
        this.currentLine = currentLine;
    }
}
