package javafxapplication1;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.scene.paint.Color;

/**
 * Rappresenta l'editor di una forma per la manipolazione e il cambiamento di proprietà.
 */
public abstract class ShapeEditor {

    private Shape shape;
    private double startX;
    private double startY;
    private double offsetX;
    private double offsetY;

    /**
     * Costruttore dell'editor per una specifica forma.
     *
     * @param shape la forma da modificare.
     * @param startX posizione iniziale X.
     * @param startY posizione iniziale Y.
     */
    
    public ShapeEditor(Shape shape, double startX, double startY) {
        this.shape = shape;
        this.startX = startX;
        this.startY = startY;
        
        
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

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }
    
    public void setOffsetX(double offsetX) {
        this.offsetX = offsetX;
    }
    
    public void setOffsetY(double offsetY) {
        this.offsetY = offsetY;
    }
    
    public abstract double getWidth();
    public abstract double getHeight();

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
    public abstract void changeHeightSize(double size);
    public abstract void changeWidthSize(double size);

    /**
     * Metodo vuoto per gestire lo spostamento della forma, potrebbe essere implementato da sottoclassi.
     *
     * @param offsetX l'offset per l'asse X.
     * @param offsetY l'offset per l'asse Y.
     */
    public void dragShape(double offsetX, double offsetY) {
        shape.setTranslateX(shape.getTranslateX() + offsetX);
        shape.setTranslateY(shape.getTranslateY() + offsetY);
    }

    
    public void moveShapeTo(double x, double y) {
        if (this.shape != null){
            shape.setTranslateX(x);
            shape.setTranslateY(y);
        }
        else{
            System.out.println("Figura non selezionata");
        }
        
    }
    
    
    /**
     * Aggiunge un effetto di ombra alla forma selezionata.
     */
    public void applyDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(javafx.scene.paint.Color.GREY);
        getShape().setEffect(dropShadow);
    }
    
    public void deleteDropShadow(){
        getShape().setEffect(null);
    }
    
    public void overlap(){
        this.getShape().toFront();
    }
    public void underlap(){
        
    }
}

