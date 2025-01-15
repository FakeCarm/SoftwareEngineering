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

    @FXML
    private AnchorPane anchorPanePaper;
    @FXML
    private AnchorPane anchorPaneBar;
    
    
    // SELETTORE DI COLORI
    @FXML
    private ColorPicker colorPickerStroke;
    @FXML
    private ColorPicker colorPickerFill;
    
    private Paper drawingPaper; 
    // FORME GEOMETRICHE
    //private LineTool lineTool;
    //private RectangleTool rectangleTool;
    
    
    // STATI SISTEMA
    private ToolState state;
    
    //private boolean lineMode = false;
    //private boolean rectangleMode = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.drawingPaper = new Paper(this.anchorPanePaper);
        

        //Inizializzazione Tools
        //lineTool = new LineTool(anchorPanePaper);
        //rectangleTool = new RectangleTool(anchorPanePaper);
        
        //Settaggio funzioni Mouse su Paper
        /*
        anchorPanePaper.setOnMousePressed(lineTool::onMousePressed);
        anchorPanePaper.setOnMouseDragged(lineTool::onMouseDragged);
        anchorPanePaper.setOnMouseReleased(lineTool::onMouseReleased);
        */
        
        /*
        colorPickerLine.setOnAction(event -> {
            lineTool.setLineColor(colorPickerLine.getValue());
            rectangleTool.setRectangleStrokeColor(colorPickerLine.getValue());
        });
        
        colorPickerFill.setOnAction(event -> {
            rectangleTool.setRectangleFillColor(colorPickerFill.getValue());
        });  
        */
                
        
    }

    
    /*
    @FXML
    private void handleLineButtonAction() {
        lineMode = true;
        rectangleMode = false;
        System.out.println("Strumento Linea attivato.");
    }
    */
    
    
    // BOTTONI. La pressione del bottone imposta lo stato del sistema che deve lavorare.
    @FXML
    public void handleLineButtonAction() {
        this.state = new LineTool(drawingPaper, anchorPaneBar,colorPickerStroke.getValue(),colorPickerFill.getValue());
        System.out.println("Strumento Linea attivato.");   
    }
    
    
    @FXML
    public void handleRectangleButtonAction() {
        /*
        this.state = new RectangleTool(drawingPaper,anchorPaneBar,colorPickerStroke.getValue(),colorPickerFill.getValue());
        //Shape shape = state.getShape()
        System.out.println("Strumento Rettangolo Selezionato");
        */
        
        
    }
    
    
    
    @FXML 
    public void colorPickerStrokeAction(){
        System.out.println("Strumento Colore Stroke Selezionato");
        
    }
    
    @FXML 
    public void colorPickerFillAction(){
        System.out.println("Strumento Colore Fill Selezionato");    
    }
    
    
    // MOUSE SUL FOGLIO DA DISEGNO
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
    
    
    
    
    
    
    // COLOR PICKER
    /*
    @FXML 
    private void colorPickerStrokeAction(){
        Color colorStroke = colorPickerStroke.getValue();
        
        if (colorStroke != null){
            System.out.println("Colore Stroke Selezionato " + colorStroke.toString());
        }
        else{
            System.out.println("STROKE NULLO ");
        }
        if (this.state != null){
            state.setStrokeColor(colorStroke);
        }
    }
    
    @FXML 
    private void colorPickerFillAction(){
        Color colorFill = colorPickerFill.getValue();
        if (colorFill != null){
            System.out.println("Colore Fill Selezionato " + colorFill.toString());
        }
        else{
            System.out.println("FILL NULLO ");
        }
        
        
        
        
    }
    */
    
    
    /*
    @FXML
    priv@FXML
    private void handleRectangleButtonAction() {
        this.state = new RectangleTool(anchorPanePaper);
        
    }ate void handleRectangleButtonAction() {
        rectangleMode = true;
        lineMode = false;
        System.out.println("Strumento Rettangolo attivato.");
    }
    */
    
    
    /*
    @FXML
    private void onMousePressedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMousePressed(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMousePressed(event);
        }
        
    }
    */
    
    
    
    
    
    
    
    // VECCHI ON MOUSE PRESSED/DRAGGED
/*
    @FXML
    private void onMouseDraggedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMouseDragged(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMouseDragged(event);
        }
        
    }
    */
    
    
     /*
    @FXML
    private void onMouseReleasedPaper(MouseEvent event) {
        if (lineMode == true && rectangleMode == false){
            lineTool.onMouseReleased(event);
        }
        
        if (lineMode == false && rectangleMode == true){
            rectangleTool.onMouseReleased(event);
        }
    }
    */
}
