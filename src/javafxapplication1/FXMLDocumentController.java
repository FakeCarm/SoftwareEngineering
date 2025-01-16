package javafxapplication1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPanePaper;
    @FXML
    private AnchorPane anchorPaneBar;
    
    private FileManager fm;
    @FXML
    private ColorPicker colorPickerStroke;
    @FXML
    private ColorPicker colorPickerFill;
    private Paper drawingPaper; 
    private ToolState state;
    @FXML
    private Button lineButton;
    @FXML
    private Button rectangleButton;
    @FXML
    private Button ellipseButton;
    @FXML
    private MenuButton fileMenuSelector;
    @FXML
    private MenuItem saveSelectorButton;
    @FXML
    private MenuItem loadSelectorButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawingPaper = new Paper(this.anchorPanePaper);
        this.fm = new FileManager(this.drawingPaper);

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
    
    @FXML 
    public void colorPickerStrokeAction(){
        System.out.println("Strumento Colore Stroke Selezionato");
        
    }
    
    @FXML 
    public void colorPickerFillAction(){
        System.out.println("Strumento Colore Fill Selezionato");    
    }

    @FXML
    public void handleLineButtonAction() {
        this.state = new LineTool(drawingPaper, anchorPaneBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Linea attivato."); 
    }

    @FXML
    public void handleRectangleButtonAction() {
        state = new RectangleTool(drawingPaper, anchorPaneBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Rettangolo attivato.");
    }

    @FXML
    public void handleEllipseButtonAction() {
        this.state = new EllipseTool(drawingPaper, anchorPaneBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Ellisse attivato.");
    }

    @FXML
    private void handleLoadButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Load");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("bin files", "*.dnp"));
        File f = fc.showOpenDialog(anchorPanePaper.getScene().getWindow());
        try {
            fm.load(f);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore durante il caricamento: " + ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void handleSaveButton(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Save");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("bin files", "*.dnp"));
        File f = fc.showSaveDialog(anchorPanePaper.getScene().getWindow());
        try {
            fm.save(f);
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore durante il salvataggio: " + ex.getMessage());
            alert.show();
        }
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
}
