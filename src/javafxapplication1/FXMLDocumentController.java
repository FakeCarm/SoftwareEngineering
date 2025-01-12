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

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private Canvas drawingCanvas;    
    // Variabili di stato
    private boolean isLineMode = false;   
    private boolean isFirstClick = true;  
    private double startX, startY;        

    @FXML
    private Button lineButton;       
    
    
    
    @FXML
    private void handleLineButtonAction(ActionEvent event) {
        
        isLineMode = true;
        isFirstClick = true;
        System.out.println("Modalità Line attivata.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawingCanvas.setOnMouseClicked(this::handleCanvasClick);
    }

    
    private void handleCanvasClick(javafx.scene.input.MouseEvent event) {
    if (isLineMode) {
        if (isFirstClick) {
            
            startX = event.getX();
            startY = event.getY();
            isFirstClick = false;
        } else {
            
            double endX = event.getX();
            double endY = event.getY();

            
            drawingCanvas.getGraphicsContext2D().strokeLine(startX, startY, endX, endY);

            
            isLineMode = false;
            }
        }
    }

    
}
