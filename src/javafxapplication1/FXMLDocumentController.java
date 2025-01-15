package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class FXMLDocumentController implements Initializable {
    
    // --------- FXML VARIABILI ------------------
    @FXML
    private AnchorPane anchorPanePaper;
    @FXML
    private AnchorPane anchorPaneBar;
    
    
            // SELETTORE DI COLORI
    @FXML
    private ColorPicker colorPickerStroke;
    @FXML
    private ColorPicker colorPickerFill;
    // -------------------------------------------
    
    
    
    private Paper drawingPaper; 
    
    
    
    // STATI SISTEMA
    private ToolState state;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.drawingPaper = new Paper(this.anchorPanePaper);
        
        // Aggiunta del Listener sui ColorPicker per aggiornamento automatico del colore quando si rimane selezionati su una forma
        colorPickerStroke.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (state instanceof SelectedShapeTool) {
                ((SelectedShapeTool) state).strokeColor.set(newValue);
            }
        });

        colorPickerFill.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (state instanceof SelectedShapeTool) {
                ((SelectedShapeTool) state).fillColor.set(newValue);
            }
        });
                
        
    }

      
    // Gestione azioni interfaccia
    @FXML
    public void handleLineButtonAction() {
        this.state = new LineTool(drawingPaper, anchorPaneBar,colorPickerStroke.getValue(),colorPickerFill.getValue()); 
        System.out.println("Strumento Linea attivato."); 
    }
    
    @FXML
    public void handleRectangleButtonAction() {
        state = new RectangleTool(drawingPaper, anchorPaneBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Rettangolo attivato.");
    }

    @FXML 
    public void colorPickerStrokeAction(){
        System.out.println("Strumento Colore Stroke Selezionato");
        
    }
    
    @FXML 
    public void colorPickerFillAction(){
        System.out.println("Strumento Colore Fill Selezionato");    
    }
    
    
    // PRESSIONE SU FOGLIO DA DISEGNO SUL FOGLIO DA DISEGNO
    @FXML
    public void onMousePressedPaper(MouseEvent event) {
        
        if (this.state != null){
            state.onMousePressed(event);
        }
    }

    @FXML
    public void onMouseDraggedPaper(MouseEvent event) {
        if (this.state != null){
            state.onMouseDragged(event);
        }
    }

    @FXML
    public void onMouseReleasedPaper(MouseEvent event) {
        System.out.println("NUMERO DI ELEMENTI PRESENTI " + anchorPanePaper.getChildren().size());
        if (this.state != null){
            state.onMouseReleased(event);
        }
    }
    
}
