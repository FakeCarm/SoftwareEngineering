package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Gestisce l'editor per la modifica delle forme di tipo Rectangle.
 */
public class RectangleShapeEditor extends ShapeEditor {

    private Rectangle rectangle;

    /**
     * Costruttore che inizializza l'editor per il rettangolo.
     *
     * @param shape       la forma {@link Rectangle} da modificare.
     * @param paneEditor  il pannello dell'editor per la modifica.
     * @param startX      la coordinata X iniziale del rettangolo.
     * @param startY      la coordinata Y iniziale del rettangolo.
     */
    public RectangleShapeEditor(Shape shape, Pane paneEditor, double startX, double startY) {
        super(shape, paneEditor, startX, startY);
        this.rectangle = (Rectangle) shape;
    }

    /**
     * Gestisce il movimento del rettangolo spostandolo in base agli offset.
     *
     * @param offsetX l'offset di movimento lungo l'asse X.
     * @param offsetY l'offset di movimento lungo l'asse Y.
     */
    @Override
    public void dragShape(double offsetX, double offsetY) {
        rectangle.setTranslateX(rectangle.getTranslateX() + offsetX);
        rectangle.setTranslateY(rectangle.getTranslateY() + offsetY);
    }

    
    @Override
    public void changeHeightSize(double size){
        this.rectangle.setHeight(size);
    }
    
    @Override
    public void changeWidthSize(double size){
        this.rectangle.setWidth(size);
    }
}
