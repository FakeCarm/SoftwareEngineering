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

    /**
     * Costruttore che inizializza l'area di lavoro.
     *
     * @param anchorPanePaper il pannello di tipo AnchorPane in cui le forme vengono visualizzate.
     */
    public Paper(AnchorPane anchorPanePaper, BorderPane borderPane) {
        this.anchorPanePaper = anchorPanePaper;
        this.borderPane = borderPane;
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
}
