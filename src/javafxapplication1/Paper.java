package javafxapplication1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;

/**
 * Rappresenta l'area di lavoro su cui vengono aggiunte o rimosse forme.
 */
public class Paper {
    
    private BorderPane borderPane;
    private AnchorPane anchorPanePaper;
    private double zoomFactor = 0.2;
    public static final double MIN_ZOOM = 1;
    public static final double MAX_ZOOM = 1 + 0.2*8;
    private GridCanvas gridCanvas;
    private boolean isGridVisible = false;

    /**
     * Costruttore che inizializza l'area di lavoro.
     *
     * @param anchorPanePaper il pannello di tipo AnchorPane in cui le forme vengono visualizzate.
     */
    public Paper(AnchorPane anchorPanePaper, BorderPane borderPane) {
        this.anchorPanePaper = anchorPanePaper;
        this.borderPane = borderPane;
        this.gridCanvas = new GridCanvas(anchorPanePaper);
        this.gridCanvas.setWidth(anchorPanePaper.getPrefWidth());
        this.gridCanvas.setHeight(anchorPanePaper.getPrefHeight());
        this.anchorPanePaper.widthProperty().addListener((obs, oldVal, newVal) -> {
            gridCanvas.setWidth(newVal.doubleValue());
            gridCanvas.draw();
        });
        this.anchorPanePaper.heightProperty().addListener((obs, oldVal, newVal) -> {
            gridCanvas.setHeight(newVal.doubleValue());
            gridCanvas.draw();
        });
    }

    /**
     * Aggiunge una forma all'area di lavoro.
     *
     * @param shape la forma da aggiungere.
     */
    public void addOnPaper(Shape shape) {
        this.anchorPanePaper.getChildren().add(shape);
    }

    /**
     * Rimuove una forma dall'area di lavoro.
     *
     * @param shape la forma da rimuovere.
     */
    public void removeOnPaper(Shape shape) {
        this.anchorPanePaper.getChildren().remove(shape);
    }
    
    public void zoomOnPaper(){
        
        double zoomX= this.anchorPanePaper.getScaleX() + zoomFactor;
        double zoomY = this.anchorPanePaper.getScaleY() + zoomFactor;
        
        if(zoomX >= MAX_ZOOM && zoomY >= MAX_ZOOM){
            this.anchorPanePaper.setScaleX(MAX_ZOOM);
            this.anchorPanePaper.setScaleY(MAX_ZOOM);
            System.out.println("Limite zoom raggiunto.");
            
        }
        else{
            this.anchorPanePaper.setScaleX(anchorPanePaper.getScaleX() + zoomFactor);
            this.anchorPanePaper.setScaleY(anchorPanePaper.getScaleY() + zoomFactor);
        }
        
    }
    
    public void unzoomOnPaper(){
        double  zoomX= this.anchorPanePaper.getScaleX() - zoomFactor;
        double zoomY = this.anchorPanePaper.getScaleY() - zoomFactor;
         
        if(zoomX < MIN_ZOOM && zoomY < MIN_ZOOM){
            this.anchorPanePaper.setScaleX(MIN_ZOOM);
            this.anchorPanePaper.setScaleY(MIN_ZOOM);
            System.out.println("Limite Unzoom Raggiunto.");
        }
        else{
            this.anchorPanePaper.setScaleX(zoomX);
            this.anchorPanePaper.setScaleY(zoomY);
        }
    }

    /**
     * Restituisce il pannello di lavoro.
     *
     * @return l'istanza di AnchorPane}
     */
    public AnchorPane getAnchorPanePaper() {
        return anchorPanePaper;
    }
    
    /**
     * Imposta un nuovo pannello di lavoro.
     *
     * @param anchorPanePaper il nuovo pannello di lavoro.
     */
    public void setAnchorPanePaper(AnchorPane anchorPanePaper) {
        this.anchorPanePaper = anchorPanePaper;
    }
    
    public BorderPane getBorderPane() {
        return borderPane;
    }
    
    public void updateGridOnMove(double newX, double newY) {
        double currentWidth = gridCanvas.getWidth();
        double currentHeight = gridCanvas.getHeight();

        boolean needsResize = false;

        if (newX > currentWidth - 50) { // Espande la griglia se la figura si avvicina al bordo destro
            gridCanvas.setWidth(newX + 100);
            needsResize = true;
        }

        if (newY > currentHeight - 50) { // Espande la griglia se la figura si avvicina al bordo inferiore
            gridCanvas.setHeight(newY + 100);
            needsResize = true;
        }

        if (needsResize) {
            gridCanvas.draw();
        }
    }
    
    public void showGrid() {
        if (!anchorPanePaper.getChildren().contains(gridCanvas)) {
            anchorPanePaper.getChildren().add(0, gridCanvas); 
            isGridVisible = true;
        }
    }

    public void hideGrid() {
        anchorPanePaper.getChildren().remove(gridCanvas);
        isGridVisible = false;
    }
    
    public boolean isGridVisible() {
        return isGridVisible;
    }
    public void expandGrid(double newWidth, double newHeight) {
        if (gridCanvas != null) {
            gridCanvas.resizeGrid(newWidth, newHeight);
        }
    }
    
    public GridCanvas getGridCanvas() {
        return gridCanvas;
    }


}
