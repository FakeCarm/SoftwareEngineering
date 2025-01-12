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

    @FXML
    private Button lineButton;       
    
    
    // Questo metodo è legato a onAction="#handleLineButtonAction"
    @FXML
    private void handleLineButtonAction(ActionEvent event) {
        
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();

        gc.setStroke(Color.BLACK);

        gc.strokeLine(10, 10, 200, 200);
        
        System.out.println("Linea disegnata!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inizializza qui se hai bisogno di logica di start-up
    }    
    
}
