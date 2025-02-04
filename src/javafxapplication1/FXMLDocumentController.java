package javafxapplication1;

import Command.BringToFront;
import Command.PasteShape;
import Command.CutShape;
import Command.CopyShape;
import Command.ChangeFillColor;
import Command.ChangeHeight;
import Command.ChangeStrokeColor;
import Command.ChangeWidth;
import Command.DeleteShape;
import Command.Invoker;
import Command.MirrorShape;
import Command.RotateShape;
import Command.SendToBack;
import Command.UndoRedoListener;
import Command.Unzoom;
import Command.Zoom;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;


public class FXMLDocumentController implements Initializable, UndoRedoListener {

    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPanePaper;
    @FXML
    private ToolBar toolBar;
    @FXML
    private AnchorPane paneEditor;
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
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private Button overlapButton;
    @FXML
    private Button underlapButton;
    @FXML
    private Button unzoomButton;
    @FXML
    private Button zoomButton;
    @FXML
    private Button gridToggleButton;
    @FXML
    private Button increaseGridButton;
    @FXML
    private Button decreaseGridButton;
    @FXML
    private Button mirrorVerticalButton;
    @FXML
    private Button mirrorHorizontalButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button selectionButton;
    @FXML
    private TextField rotationTextField;
    private ContextMenu figureContextMenu;
    private ContextMenu paperContextMenu;
    
    

    
    
    
    private FileManager fm;
    private Paper drawingPaper; 
    private ToolState state;
    private boolean isGridVisible = false;
    
    private double pasteClickX;
    private double pasteClickY;
   



    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.drawingPaper = new Paper(this.anchorPanePaper,this.borderPane, this.paneEditor);
        this.fm = new FileManager(this.drawingPaper);
        this.borderPane.setRight(null);
        
        Group paneGroup = new Group(anchorPanePaper);
        scrollPane.setContent(paneGroup);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        mirrorVerticalButton.setDisable(true);
        mirrorHorizontalButton.setDisable(true);
        scrollPane.viewportBoundsProperty().addListener((observable, oldValue, newValue)->{
        anchorPanePaper.setPrefSize(newValue.getWidth(), newValue.getHeight());
        });
        // Inizializzazione dei menu contestuali
        initializeContextMenus();
   
                
        colorPickerStroke.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (state instanceof SelectedShapeTool) {
                ((SelectedShapeTool) state).setStrokeColor((Color) newValue);
            }
        });

        colorPickerFill.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (state instanceof SelectedShapeTool) {
                ((SelectedShapeTool) state).setFillColor(newValue);
            }
        });
        
        
            
        
        
        Invoker invoker = Invoker.getInvoker();
        invoker.setUndoRedoListener(this);
          
        
        
        heightTextField.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d{0,3}(\\.\\d{0,2})?")) { 
        return change;
        }
        return null;
        
        }));
        
        widthTextField.setTextFormatter(new TextFormatter<>(change -> {
        String newText = change.getControlNewText();
        if (newText.matches("\\d{0,3}(\\.\\d{0,2})?")) { 
            return change;
        }
        return null;
        
        }));
        
        anchorPanePaper.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.drawingPaper.getGridCanvas().resizeGrid(newVal.doubleValue(), anchorPanePaper.getHeight());
        });

        anchorPanePaper.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.drawingPaper.getGridCanvas().resizeGrid(anchorPanePaper.getWidth(), newVal.doubleValue());
        });
        increaseGridButton.setDisable(true);
        decreaseGridButton.setDisable(true);

        
        

    }
    
    /**
     * Metodo che consente di inizializzare il piccolo menu relativo al click col tasto destro
     */
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
    
    /**
     * Metodo che all'alla scelta del colore nella finestra di editor, cambia il colore della figura selezionata
     */
    @FXML 
    public void colorPickerEditorStrokeAction(){
        System.out.println("Modifica Colore Stroke Selezionato");
        if(this.state != null && state instanceof SelectionTool){
            SelectionTool selectionTool = (SelectionTool) state;
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new ChangeStrokeColor(selectionTool.getEditor(),colorPickerEditorStroke.getValue()));
        }
        
    }
    
    /**
     * Metodo che all'alla scelta del colore nella finestra di editor, cambia il colore della figura selezionata
     */
    @FXML 
    public void colorPickerEditorFillAction(){
        System.out.println("Modifica Colore Fill Selezionato");  
        if(this.state != null && state instanceof SelectionTool){
            SelectionTool selectionTool = (SelectionTool) state;
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new ChangeFillColor(selectionTool.getEditor(), colorPickerEditorFill.getValue()));
        }
    }
    
    /**
     * Metodo che consente di selezionare lo strumento Linea
     */
    @FXML
    public void handleLineButtonAction() {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        
        this.state = new LineTool(drawingPaper,colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Linea attivato."); 
        
        
    }
    
    /**
     * Metodo che consente di selezionare lo strumento rettangolo
     */
    @FXML
    public void handleRectangleButtonAction() {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        state = new RectangleTool(drawingPaper,colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Rettangolo attivato."); 
        
        
    }
    
    /**
     * Metodo che consente di selezionare lo strumento Ellisse
     */
    @FXML
    public void handleEllipseButtonAction() {
        
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        
        this.state = new EllipseTool(drawingPaper,colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Ellisse attivato.");
        
        
    }
    
    /**
     * Metodo che consente di selezionare lo strumento Poligono
     */
    @FXML
    public void handlePolygonButtonAction() {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
            
        }
        this.state = new PolygonTool(drawingPaper,colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Poligono attivato.");
        
    }
    
    /**
     * Metodo che consente di selezionare lo strumento Testo
     */
    @FXML
    public void handleTextButtonAction() {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        
        this.state = new TextTool(drawingPaper,colorPickerStroke.getValue(), colorPickerFill.getValue());
        System.out.println("Strumento Testo attivato.");
    }
    
    /**
     * Metodo che consente di caricare un nuovo disegno alla pressione del tasto carica
     */
    @FXML
    private void handleLoadButton(ActionEvent event) {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        
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
    
    /**
     * Metodo che consente di salvare il disegno alla pressione del tasto salva
    */
    @FXML
    private void handleSaveButton(ActionEvent event) {
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
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
    
    
    /**
     * Metodo che consente di selezionare lo strumento "Seleziona"
     * @param event 
     */
    @FXML 
    private void handleSelectionButtonAction(ActionEvent event){
        if (this.state instanceof SelectionTool){
            SelectionTool tool = (SelectionTool) this.state;
            ShapeEditor editor = tool.getEditor();
            if(editor != null){
                tool.getEditor().deleteDropShadow();
            }
        }
        state = new SelectionTool(drawingPaper);
        System.out.println("Strumento Selezione attivato.");
    }
    
    /**
     * Metodo che consente di cancellare una figura selezionata alla pressione del bottone "Rimuovi"
    */
    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();
                    
            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new DeleteShape(drawingPaper, selectedShape)); 
                System.out.println("Shape rimossa: " + selectedShape.getId());
                //selectionTool.getEditor().getShape().setEffect(null); 
            } else {
                System.out.println("Nessuna figura selezionata.");
            }
        } else {
            System.out.println("Attiva lo strumento di selezione per rimuovere una figura.");
        }
    }
    
    /**
     * Metodo che gestisce la pressione del mouse sul foglio da disegno
     * @param event 
     */
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
        
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            ShapeEditor editorShape = selectionTool.getEditor();
            if (editorShape != null) {
                System.out.println("1");
                mirrorVerticalButton.setDisable(false);
                mirrorHorizontalButton.setDisable(false);
                Shape shape = editorShape.getShape();
                if(shape != null){
                    shape.setOnMousePressed(eventText -> {
                        widthTextField.setText(String.valueOf(editorShape.getWidth()));
                        heightTextField.setText((String.valueOf(editorShape.getHeight())));
                        this.colorPickerEditorStroke.setValue((Color) shape.getStroke());
                        this.colorPickerEditorFill.setValue((Color)shape.getFill());
                        this.rotationTextField.setText(String.valueOf(shape.getRotate()));
                    });         
                }     
            }    
            } else {
                mirrorVerticalButton.setDisable(true);
                mirrorHorizontalButton.setDisable(true);
            }
    }
    

    /**
     * Metodo che consente di gestire il drag sul foglio da disegno
     * @param event 
     */
    @FXML
    public void onMouseDraggedPaper(MouseEvent event) {
        if (this.state != null){
            state.onMouseDragged(event);
        }
    }
    
    /**
     * Metodo che consente di gestire lo stato al rilascio del mouse sul foglio da disegno
     * @param event 
     */
    @FXML
    public void onMouseReleasedPaper(MouseEvent event) {
        if (this.state != null) {
            state.onMouseReleased(event);
            
        }
       
    }
    
    /**
     * Metodo che consente di gestire il comportamento riguardante la pressione di tasti quando il foglio da disegno è selezionato
     * @param event 
     */
    @FXML
    public void onKeyPressedPaper(KeyEvent event){
        if (this.state != null){
            if(this.state instanceof TextTool){
                TextTool tool = (TextTool) this.state;
                String lettera = event.getText();
                if(lettera != null){
                    tool.setText(tool.getText()+lettera);
                   // recupero il testo precedente e aggiungo la lettera letta
                }
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    String text = tool.getText();
                    if (text.length() > 0) {
                        tool.setText(text.substring(0, text.length() - 1));  // rimuovo l'ultima lettera.
                    }
                }    
            }else if(this.state instanceof SelectionTool){
                SelectionTool tool = (SelectionTool) this.state;
                String lettera = event.getText();
                if (tool.getEditor() instanceof TextShapeEditor){
                 TextShapeEditor editor = (TextShapeEditor) tool.getEditor();
                    if(lettera != null){
                       editor.setText(editor.getText()+lettera);
                    }
                    if (event.getCode() == KeyCode.BACK_SPACE) {
                        String text = editor.getText();
                        if (text.length() > 0) {
                            editor.setText(text.substring(0, text.length() - 1));  // rimuovo l'ultima lettera.
                        }
                    }   
                }       
            }
        }
    }
    
    /**
     * Metodo che consente di copiare la figura selezionata alla pressione del tasto copia presente all'interno del menu a comparsa
     */
    private void handleCopy() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            if (selectionTool.getEditor() != null && selectionTool.getEditor().getShape() != null) {
                Shape selectedShape = selectionTool.getEditor().getShape();
                System.out.println("STAMPA STROKE " + selectedShape.getStrokeWidth());
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CopyShape(selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per copiare.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }
    
    /**
     * Metodo che consente di tagliare la figura alla pressione del tasto taglia presente all'interno del menu a comparsa
     */
    private void handleCut() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            if (selectionTool.getEditor() != null && selectionTool.getEditor().getShape() != null) {
                Shape selectedShape = selectionTool.getEditor().getShape();
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new CutShape(drawingPaper, selectedShape));
            } else {
                System.out.println("Nessuna figura selezionata per tagliare.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }

    
    /**
     * Metodo che consente di incollare la figura copiata alla pressione del tasto incolla presente all'interno del menu a comparsa
     */
    private void handlePaste() {
        Clipboard clipboard = Clipboard.getInstance();
        Shape copiedShape = clipboard.getCopiedShape();
        
        if (copiedShape != null) {
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new PasteShape(drawingPaper, pasteClickX, pasteClickY));
        } else {
            System.out.println("Clipboard vuota. Nessuna figura da incollare.");
        }
    }
    
    /**
     * Metodo che consente di annullare l'ultima operazione alla pressione del tasto undo nella barra strumenti
     * @param event 
     */
    @FXML
    private void handleUndoButtonAction(ActionEvent event) {
        Invoker invoker = Invoker.getInvoker();
        invoker.undo();
    }
    
    /**
     * Metodo che consente di svolgere l'ultima operazione fatta alla pressione del tasto redo presente nella barra degli strumenti
     * @param event 
     */
    @FXML
    private void handleRedoButtonAction(ActionEvent event) {
        Invoker invoker = Invoker.getInvoker();
        invoker.redo();
    }  
    
    /**
     * Metodo che consente di cambiare la condizione dei tasti redo e undo
     * @param canUndo
     * @param canRedo 
     */
    @Override
    public void onUndoRedoStateChanged(boolean canUndo, boolean canRedo) {
        undoButton.setDisable(!canUndo);
        redoButton.setDisable(!canRedo);
    }


    /**
     * Meotodo che consente di gestire la modifica dell'altezza di una figura selezionata
     * @param event 
     */
    @FXML
    public void onKeyReleasedHeight(KeyEvent event) {   
        String height = this.heightTextField.getText();
        if(height != null && !height.trim().isEmpty()){
           double value = Double.parseDouble(height);
            System.out.println("Valore inserito (double): " + value);
            if (state != null && state instanceof SelectionTool){
                SelectionTool s = (SelectionTool) state;
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new ChangeHeight(s.getEditor(),value));
         
            } 
        }
    }
    
    /**
     * Metodo che consente di gestire la modifica della lunghezza di una figura selezionata
     * @param event 
     */
    @FXML
    public void onKeyReleasedWidth(KeyEvent event) {   
        String height = this.widthTextField.getText();
        if(height != null && !height.trim().isEmpty()){
           double value = Double.parseDouble(height);
            System.out.println("Valore inserito (double): " + value);
            if (state != null && state instanceof SelectionTool){
                SelectionTool s = (SelectionTool) state;
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new ChangeWidth(s.getEditor(),value));
         
            } 
        }
    }
    
    /**
     * Metodo che consente di gestire la pressione del mouse sulla barra degli strumenti
     * @param event 
     */
    @FXML
    public void onMousePressedToolBar(MouseEvent event) {
        if(this.state instanceof SelectionTool){
            SelectionTool selectionTool = (SelectionTool) this.state;
            selectionTool.resetShapeEditor();
        }
    }
    
    /**
     * Metodo che consente di sovrapporre una figura selezionata alla pressione del bottone presente nella finestra editor
     * @param event 
     */
    @FXML
    public void onMousePressedOverlap(MouseEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();
            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new BringToFront(drawingPaper, selectedShape));
                System.out.println("Figura portata in primo piano");
            } else {
                System.out.println("Nessuna figura selezionata per portare avanti.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }

    /**
     * Metodo che consente di sottoporre una figura selezionata alla pressione del bottone presente nella finestra editor
     * @param event 
     */
    @FXML
    public void onMousePressedUnderlap(MouseEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();
            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new SendToBack(selectedShape));
                System.out.println("Figura portata in fondo.");
            } else {
                System.out.println("Nessuna figura selezionata per portare indietro.");
            }
        } else {
            System.out.println("Lo strumento di selezione non è attivo.");
        }
    }

    /**
     * Metodo che consente di aumentare lo zoom del foglio alla pressione del bottone di zoom presente nella barra degli strumenti
     */
    @FXML
    public void handleZoomButtonAction() {
        System.out.println("Zoommando " + anchorPanePaper.getScaleX() + " " + anchorPanePaper.getScaleY());
        Invoker invoker = Invoker.getInvoker();
        invoker.executeCommand(new Zoom(this.drawingPaper));
    }
    
    /**
     * Metodo che consente di diminuire lo zoom del foglio alla pressione del bottone di zoom presente nella barra degli strumenti
     */
    public void handleUnzoomButtonAction() {
        System.out.println("Unzoommando");
        Invoker invoker = Invoker.getInvoker();
        invoker.executeCommand(new Unzoom(this.drawingPaper));
    }
    
    /**
     * Metodo che consente di visualizzare la griglia sul foglio da disegno
     * @param event 
     */
    @FXML
    private void handleGridToggleButtonAction(ActionEvent event) {
        if (isGridVisible) {
            anchorPanePaper.getChildren().remove(this.drawingPaper.getGridCanvas()); // Rimuove la griglia
            increaseGridButton.setDisable(true);
            decreaseGridButton.setDisable(true);
        } else {
            anchorPanePaper.getChildren().add(this.drawingPaper.getGridCanvas()); // Aggiunge la griglia
            this.drawingPaper.getGridCanvas().draw(); // Ridisegna per sicurezza
            increaseGridButton.setDisable(false);
            decreaseGridButton.setDisable(false);
        }

        isGridVisible = !isGridVisible; // Cambia lo stato della griglia
        System.out.println("Griglia " + (isGridVisible ? "Mostrata" : "Nascosta"));
    }
    
    /**
     * Metodo che consente di aumentare la grandezza della griglia alla pressione dell'apposito bottone nella barra degli strumenti
     */
    @FXML
    private void increaseGridSize() {
        if (isGridVisible) {
            double newSpacing = this.drawingPaper.getGridCanvas().getSpacing() + 5; // Aumenta di 5px
            this.drawingPaper.getGridCanvas().setSpacing(newSpacing);
            System.out.println("Aumento la griglia: " + newSpacing);
        }
    }

    /**
     * Metodo che consente di diminuire la grandezza della griglia alla pressione dell'apposito bottone nella barra degli strumenti
     */
    @FXML
    private void decreaseGridSize() {
        if (isGridVisible) {
            double newSpacing = this.drawingPaper.getGridCanvas().getSpacing() - 5; // Diminuisce di 5px
            this.drawingPaper.getGridCanvas().setSpacing(newSpacing);
            System.out.println("Riduzione della griglia: " + newSpacing);
        }
    }
    
    /**
     * Metodo che consente di cambiare l'angolazione della figura
     * @param event 
     */
    @FXML
    public void onRotationChanged(KeyEvent event) {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();

            if (selectedShape != null) {
                String rotationValue = rotationTextField.getText();
                if (rotationValue != null && !rotationValue.trim().isEmpty()) {
                    try {
                        double newAngle = Double.parseDouble(rotationValue);
                        if (newAngle >= 0 && newAngle <= 360) {
                            double oldAngle = selectedShape.getRotate();
                            if (oldAngle != newAngle) {
                                Invoker invoker = Invoker.getInvoker();
                                invoker.executeCommand(new RotateShape(selectedShape, oldAngle, newAngle));
                                System.out.println("Ruotata la figura a " + newAngle + "°");
                            }
                        } else {
                            System.out.println("L'angolo deve essere tra 0 e 360 gradi.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Inserisci un numero valido.");
                    }
                }
            }
        }
    }
    
    /**
     * Metodo che consente di specchiare in verticale la figura
     */
    @FXML
    private void handleMirrorVerticalAction() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();

            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new MirrorShape(selectedShape, true)); // Specchiatura verticale
                System.out.println("Specchiatura verticale applicata.");
            } else {
                System.out.println("Nessuna figura selezionata.");
            }
        } else {
            System.out.println("Attiva lo strumento di selezione per specchiare una figura.");
        }
    }
    
    /**
     * Metodo che consente di specchiare in orizzontale la figura
     */
    @FXML
    private void handleMirrorHorizontalAction() {
        if (state instanceof SelectionTool) {
            SelectionTool selectionTool = (SelectionTool) state;
            Shape selectedShape = selectionTool.getEditor().getShape();

            if (selectedShape != null) {
                Invoker invoker = Invoker.getInvoker();
                invoker.executeCommand(new MirrorShape(selectedShape, false)); // Specchiatura orizzontale
                System.out.println("Specchiatura orizzontale applicata.");
            } else {
                System.out.println("Nessuna figura selezionata.");
            }
        } else {
            System.out.println("Attiva lo strumento di selezione per specchiare una figura.");
        }
    }   
}
