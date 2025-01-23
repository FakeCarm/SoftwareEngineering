package javafxapplication1;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

/**
 * Rappresenta l'editor di una forma per la manipolazione e il cambiamento di proprietà.
 */
public class ShapeEditor {

    private Shape shape;
    private Pane paneEditor;
    private double startX;
    private double startY;
  

    /**
     * Costruttore dell'editor per una specifica forma.
     *
     * @param shape la forma da modificare.
     * @param paneEditor il pannello editor visibile.
     * @param startX posizione iniziale X.
     * @param startY posizione iniziale Y.
     */
    
    public ShapeEditor(Shape shape,Pane paneEditor, double startX, double startY) {
        this.shape = shape;
        this.startX = startX;
        this.startY = startY;
        this.paneEditor = paneEditor;
        this.paneEditor.setVisible(true);
        
    }

    // Getter e Setter per posizione e forma
    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Cambia il colore del bordo (stroke) della forma.
     *
     * @param color il colore da applicare al bordo.
     */
    public void changeStrokeColor(Color color) {
        System.out.println("CAMBIO STROKE");
        this.getShape().setStroke(color);
    }

    /**
     * Cambia il colore di riempimento della forma.
     *
     * @param color il colore da applicare come riempimento.
     */
    public void changeFillColor(Color color) {
        System.out.println("CAMBIO FILL");
        this.getShape().setFill(color);
    }

    // Metodo per cambiare la dimensione della forma, può essere esteso con logica specifica
    public void changeHeightSize(double size) {}
    public void changeWidthSize(double size){}

    public void dragShape(double offsetX, double offsetY) {}
}
