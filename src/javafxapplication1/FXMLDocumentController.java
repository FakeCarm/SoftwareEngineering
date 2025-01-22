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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
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
    

    private ContextMenu figureContextMenu;
    private ContextMenu paperContextMenu;
    private double pasteClickX;
    private double pasteClickY;


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.drawingPaper = new Paper(this.anchorPanePaper);
        this.fm = new FileManager(this.drawingPaper);
        this.paneEditor.setVisible(false);
        
        // Inizializzazione dei menu contestuali
        initializeContextMenus();
   
                
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
    
    private void initializeContextMenus() {
        // Menu contestuale per le figure
        figureContextMenu = new ContextMenu();
        MenuItem cutItem = new MenuItem("Taglia");
        MenuItem copyItem = new MenuItem("Copia");

        cutItem.setOnAction(e -> handleCut());
        copyItem.setOnAction(e -> handleCopy());

        figureContextMenu.getItems().addAll(cutItem, copyItem);

        // Menu contestuale per il foglio
        paperContextMenu = new ContextMenu();
        MenuItem pasteItem = new MenuItem("Incolla");

        pasteItem.setOnAction(e -> handlePaste());

        paperContextMenu.getItems().add(pasteItem);
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
    
    
    @FXML
    public void onMousePressedPaper(MouseEvent event) {
        // Nascondi entrambi i menu contestuali
        figureContextMenu.hide();
        paperContextMenu.hide();
        if (event.isSecondaryButtonDown()) { // Tasto destro del mouse
            Clipboard clipboard = Clipboard.getInstance();
            if (event.getTarget() instanceof Shape) {
                Shape clickedShape = (Shape) event.getTarget();
                boolean isSelectionToolActive = state instanceof SelectionTool;

                // Attiva/disattiva le opzioni in base allo stato
                figureContextMenu.getItems().get(0).setDisable(!isSelectionToolActive); // Disabilita Taglia
                figureContextMenu.getItems().get(1).setDisable(!isSelectionToolActive); // Disabilita Copia

                // Mostra il menu contestuale per la figura
                figureContextMenu.show(anchorPanePaper, event.getScreenX(), event.getScreenY());
            } else {
                // Salva la posizione del clic destro per incollare
                pasteClickX = event.getX();
                pasteClickY = event.getY();

                // Attiva/disattiva l'opzione Incolla
                paperContextMenu.getItems().get(0).setDisable(clipboard.getCopiedShape() == null); // Disabilita Incolla

                // Mostra il menu contestuale per il foglio
                paperContextMenu.show(anchorPanePaper, event.getScreenX(), event.getScreenY());
            }
        } else if (this.state != null) {
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
        if (this.state != null) {
            state.onMouseReleased(event);
        }
       
    }

    
    private void handleCopy() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            if (selectionTool.getEditor() != null && selectionTool.getEditor().getShape() != null) {
                Shape selectedShape = selectionTool.getEditor().getShape();
                System.out.println("STAMPA STROKE " + selectedShape.getStrokeWidth());
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CopyShapeCommand(drawingPaper, selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per copiare.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }

    private void handleCut() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            if (selectionTool.getEditor() != null && selectionTool.getEditor().getShape() != null) {
                Shape selectedShape = selectionTool.getEditor().getShape();
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CutShapeCommand(drawingPaper, selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per tagliare.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }


    private void handlePaste() {
        Clipboard clipboard = Clipboard.getInstance();
        Shape copiedShape = clipboard.getCopiedShape();

        if (copiedShape != null) {
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new PasteShapeCommand(drawingPaper, pasteClickX, pasteClickY));
        } else {
            System.out.println("Clipboard vuota. Nessuna figura da incollare.");
        }
    }


}
