package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPanePaper;
    
    
    // SELETTORE DI COLORI
    @FXML
    private ColorPicker colorPickerLine;
    @FXML
    private ColorPicker colorPickerFill;
    
    
    //FORME GEOMETRICHE
    private LineTool lineTool;
    private RectangleTool rectangleTool;
    
    private boolean lineMode = false;
    private boolean rectangleMode = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Inizializzazione Tools
        lineTool = new LineTool(anchorPanePaper);
        rectangleTool = new RectangleTool(anchorPanePaper);
        
        //Settaggio funzioni Mouse su Paper
        /*
        anchorPanePaper.setOnMousePressed(lineTool::onMousePressed);
        anchorPanePaper.setOnMouseDragged(lineTool::onMouseDragged);
        anchorPanePaper.setOnMouseReleased(lineTool::onMouseReleased);
        */
        
        colorPickerLine.setOnAction(event -> {
            lineTool.setLineColor(colorPickerLine.getValue());
            rectangleTool.setRectangleStrokeColor(colorPickerLine.getValue());
        });
        
        colorPickerFill.setOnAction(event -> {
            rectangleTool.setRectangleFillColor(colorPickerFill.getValue());
        });
    }

    @FXML
    private void handleLineButtonAction() {
        lineMode = true;
        rectangleMode = false;
        System.out.println("Strumento Linea attivato.");
    }
    
    
    @FXML
    private void handleRectangleButtonAction() {
        rectangleMode = true;
        lineMode = false;
        System.out.println("Strumento Rettangolo attivato.");
    }
    
    
    @FXML
    private void onMousePressedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMousePressed(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMousePressed(event);
        }
        
    }

    @FXML
    private void onMouseDraggedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMouseDragged(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMouseDragged(event);
        }
        
    }

    @FXML
    private void onMouseReleasedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMouseReleased(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMouseReleased(event);
        }
    }

}
