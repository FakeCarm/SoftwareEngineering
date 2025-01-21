package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * Editor specifico per la gestione e la modifica di una linea.
 */
public class LineShapeEditor extends ShapeEditor {

    private Line line;

    /**
     * Costruttore che inizializza l'editor per una linea.
     *
     * @param shape         la forma (linea) da modificare.
     * @param drawingPaper  l'area di lavoro associata.
     * @param paneEditor    il pannello editor utilizzato per la modifica.
     * @param startX        coordinata X iniziale.
     * @param startY        coordinata Y iniziale.
     */
    public LineShapeEditor(Shape shape, Pane paneEditor, double startX, double startY) {
        super(shape,paneEditor, startX, startY);
        this.line = (Line) shape;
    }

    /**
     * Aggiorna le coordinate della linea per trascinarla di un offset specifico.
     *
     * @param offsetX l'offset orizzontale per il trascinamento.
     * @param offsetY l'offset verticale per il trascinamento.
     */
    @Override
    public void dragShape(double offsetX, double offsetY) {
        line.setStartX(line.getStartX() + offsetX);
        line.setStartY(line.getStartY() + offsetY);
        line.setEndX(line.getEndX() + offsetX);
        line.setEndY(line.getEndY() + offsetY);
    }
}
