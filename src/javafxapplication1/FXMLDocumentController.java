package javafxapplication1;

import Command.ChangeFillColor;
import Command.ChangeStrokeColor;
import Command.DeleteShape;
import Command.Invoker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPanePaper;
    @FXML
    private ToolBar toolBar;
    @FXML
    private Pane paneEditor;
    @FXML
    private ColorPicker colorPickerStroke;
    @FXML
    private ColorPicker colorPickerFill;
    @FXML
    private ColorPicker colorPickerEditorStroke;
    @FXML
    private ColorPicker colorPickerEditorFill;
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
    
    
    
    private FileManager fm;
    private Paper drawingPaper; 
    private ToolState state;
    private ShapeEditor editor;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawingPaper = new Paper(this.anchorPanePaper);
        this.fm = new FileManager(this.drawingPaper);
        this.paneEditor.setVisible(false);
   
                
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
    //// Creo il selection Manager, mi trovo nello stato figura selezionata, ho inserito e selzionato una figura quindi l'editor è pieno. Quando viene selezionato il colore richiamo una funzione del selection Manager per modificare tale colore passandogli in input l'editor e il colore
    
    
    
    // SEZIONE BOTTONI
    @FXML 
    public void colorPickerStrokeAction(){
        System.out.println("Strumento Colore Stroke Selezionato");
        
    }
    
    @FXML 
    public void colorPickerFillAction(){
        System.out.println("Strumento Colore Fill Selezionato");    
    }
    
    @FXML 
    public void colorPickerEditorStrokeAction(){
        System.out.println("Modifica Colore Stroke Selezionato");
        if(this.state != null && state instanceof SelectionTool){
            SelectionTool selectionTool = (SelectionTool) state;
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new ChangeStrokeColor(selectionTool.getPaper(),selectionTool.getEditor().getShape(),colorPickerEditorStroke.getValue(),selectionTool.getEditor()));
        }
        
    }
    
    @FXML 
    public void colorPickerEditorFillAction(){
        System.out.println("Modifica Colore Fill Selezionato");  
        if(this.state != null && state instanceof SelectionTool){
            SelectionTool selectionTool = (SelectionTool) state;
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new ChangeFillColor(selectionTool.getPaper(),selectionTool.getEditor().getShape(),colorPickerEditorFill.getValue(),selectionTool.getEditor()));
        }
    }
    
    @FXML
    public void handleLineButtonAction() {
        this.state = new LineTool(drawingPaper, toolBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Linea attivato."); 
    }

    @FXML
    public void handleRectangleButtonAction() {
        state = new RectangleTool(drawingPaper, toolBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Rettangolo attivato.");
    }

    @FXML
    public void handleEllipseButtonAction() {
        this.state = new EllipseTool(drawingPaper, toolBar, colorPickerStroke.getValue(), colorPickerFill.getValue());
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
    
    
    @FXML 
    private void handleSelectionButtonAction(ActionEvent event){
        //this.paneEditor.setVisible(true);
        state = new SelectionTool(drawingPaper, paneEditor);
        System.out.println("Strumento Selezione attivato.");
    }
    
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();// Ottieni la figura selezionata
                    
            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new DeleteShape(drawingPaper, selectedShape)); // Esegui il comando
                System.out.println("Shape rimossa: " + selectedShape.getId());
                selectionTool.getEditor().getShape().setEffect(null); // Rimuovi l'effetto
            } else {
                System.out.println("Nessuna figura selezionata.");
            }
        } else {
            System.out.println("Attiva lo strumento di selezione per rimuovere una figura.");
        }
    }
    
    
    // MOUSE SUL FOGLIO DA DISEGNO
    @FXML
    public void onMousePressedPaper(MouseEvent event) {
       
        if (this.state != null){
            //System.out.println("PRESSIONE TASTO");
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
        //System.out.println("NUMERO DI ELEMENTI PRESENTI " + anchorPanePaper.getChildren().size());
        if (this.state != null){
            state.onMouseReleased(event);
        }
    }
/*
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getSelectedShape(); // Ottieni la figura selezionata

            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new DeleteShape(drawingPaper, selectedShape)); // Esegui il comando
                System.out.println("Shape rimossa: " + selectedShape.getId());
                selectionTool.getSelectedShape().setEffect(null); // Rimuovi l'effetto
            } else {
                System.out.println("Nessuna figura selezionata.");
            }
        } else {
            System.out.println("Attiva lo strumento di selezione per rimuovere una figura.");
        }
    }

    @FXML
    private void handleCopyButtonAction(ActionEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getSelectedShape();

            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CopyShapeCommand(drawingPaper, selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per copiare.");
            }
        }
    }

    @FXML
    private void handleCutButtonAction(ActionEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getSelectedShape();

            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CutShapeCommand(drawingPaper, selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per tagliare.");
            }
        }
    }

    @FXML
    private void handlePasteButtonAction(ActionEvent event) {
        Invoker invoker = Invoker.getInvoker();
        invoker.executeCommand(new PasteShapeCommand(drawingPaper));
    }

*/
}
