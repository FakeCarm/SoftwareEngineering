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
