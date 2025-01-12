package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.scene.shape.*;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    // Variabili di stato
    private boolean isLineMode = false;   
    private boolean isFirstClick = true;  
    private double startX, startY; 
    private GraphicsContext gc;
    private Line linea;
    
    @FXML
    private AnchorPane anchorPanePaper;
    
    
    @FXML
    private Button lineButton;       
    
    
    
    @FXML
    private void handleLineButtonAction(ActionEvent event) {
        
        isLineMode = true;
        isFirstClick = true;
        System.out.println("Modalità Line attivata.");

        
        
    }
    
    @FXML
    private void onMousePressedPaper(javafx.scene.input.MouseEvent event){
        if (isLineMode) {
          this.startX = event.getX();
          this.startY = event.getY();
          Line newlinea = new Line();
          this.linea = newlinea;
          this.linea.setStartX(this.startX);
          this.linea.setStartX(this.startY);
          //this.linea.setEndX(this.startX);
          //this.linea.setEndY(this.startY);
          this.linea.setStroke(Color.BLUE);
          this.linea.setStrokeWidth(3);
          //this.anchorPanePaper.getChildren().add(linea);
          System.out.println("Posizione iniziale settata");  
        }
        
        
    }
    
    
    @FXML
    private void onMouseDraggedPaper(javafx.scene.input.MouseEvent event){
        if (isLineMode) {
            double startXX = event.getX();
            double startYY = event.getY();
            
            this.linea.setEndX(startXX);
            this.linea.setEndY(startYY);
            this.anchorPanePaper.getChildren().add(this.linea);
            //drawingCanvas.getGraphicsContext2D().strokeLine(this.startX, this.startY, startXX, startYY);
          
        } 
        
    }
    
    @FXML
    private void onMouseReleasedPaper(javafx.scene.input.MouseEvent event){
        if (isLineMode) {
            double endX = event.getX();
            double endY = event.getY();
            this.linea.setEndX(endX);
            this.linea.setEndY(endY);
            
               
        }
        
        
    }
   
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        this.anchorPanePaper.setOnMousePressed(this::onMousePressedPaper);
        this.anchorPanePaper.setOnMouseDragged(this::onMouseDraggedPaper);
        this.anchorPanePaper.setOnMouseReleased(this::onMouseReleasedPaper);
    }

    /*
    private void handleCanvasClick(javafx.scene.input.MouseEvent event) {
    if (isLineMode) {
        if (isFirstClick) {
            
            startX = event.getX();
            startY = event.getY();
            isFirstClick = false;
        }
        else{
            
            double endX = event.getX();
            double endY = event.getY();

            
            drawingCanvas.getGraphicsContext2D().strokeLine(startX, startY, endX, endY);

            
            isLineMode = false;
            }
        }
    }
*/
     
    
}
