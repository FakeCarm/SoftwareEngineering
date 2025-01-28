package javafxapplication1;

import Command.Invoker;
import Command.DragShape;
import java.awt.Color;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


/**
 * Rappresenta lo strumento di selezione per la manipolazione di forme.
 */
public class SelectionTool extends ToolState {
    
   
    private Paper paper;
    private ShapeEditor shapeEditor;
    boolean foundShape;
    
    
    private double pressX, pressY;  
    private double shapeStartTx, shapeStartTy; 


    private AnchorPane paneEditor;


    /**
     * Costruttore per l'inizializzazione dello strumento di selezione.
     *
     * @param paper il foglio su cui lavorare.
     * @param paneEditor il pannello dove la selezione è visibile.
     */
    public SelectionTool(Paper paper, AnchorPane paneEditor) {
        this.paper = paper;
        this.shapeEditor = null;
        this.paneEditor = paneEditor;
    }

    /**
     * Gestisce l'evento di pressione del mouse per selezionare una forma.
     */
    @Override
    public void onMousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        foundShape = false;
        ObservableList<Node> lista = paper.getAnchorPanePaper().getChildren();

        // Verifica se una forma è stata selezionata
        for (Node node : lista) {
           
            if (node instanceof Shape) {
                Shape s = (Shape) node;
                double actualX = x - s.getTranslateX();
                double actualY = y - s.getTranslateY();

                if (s.contains(actualX, actualY)) {
                    // Se è stata selezionata una nuova forma allora resetta la vecchia
                    resetShapeEditor();

                    // Seleziona il tipo di forma per l'editing
                    if (s instanceof Ellipse) {
                        shapeEditor = new EllipseShapeEditor(s, x, y);
                    } else if (s instanceof Rectangle) {
                        shapeEditor = new RectangleShapeEditor(s, x, y);
                    } else if (s instanceof Line) {
                        shapeEditor = new LineShapeEditor(s, x, y);
                    } else if (s instanceof Text){
                        System.out.println("TESTO Selezionato");
                        shapeEditor = new TextEditor(s,x,y);
                    }
                    
                    pressX = x;
                    pressY = y;
                    shapeStartTx = s.getTranslateX();
                    shapeStartTy = s.getTranslateY();

                    // Aggiungi un effetto visivo alla forma selezionata
                    if (shapeEditor != null) {
                        this.paneEditor.setVisible(true);   //Se faccio paneEditor.setDisable(false) i figli non si abilitano
                        if(shapeEditor instanceof TextEditor){      
                            this.paneEditor.lookup("#widthLabel").setDisable(true);
                            this.paneEditor.lookup("#widthTextField").setDisable(true);
                            this.paneEditor.lookup("#strokeImage").setDisable(true);
                            this.paneEditor.lookup("#colorPickerEditorStroke").setDisable(true);
                        }else{
                            this.paneEditor.lookup("#widthLabel").setDisable(false);
                            this.paneEditor.lookup("#widthTextField").setDisable(false);
                            this.paneEditor.lookup("#strokeImage").setDisable(false);
                            this.paneEditor.lookup("#colorPickerEditorStroke").setDisable(false);
                            }
                        
                        foundShape = true;
                        shapeEditor.applyDropShadow();
                        break;
                    }
                }
            }
        }
        // Reset editor se nessuna forma è selezionata
        if (!foundShape) {
            resetShapeEditor();
        }
    }

    /**
     * Gestisce il trascinamento della forma selezionata.
     */
    @Override
    public void onMouseDragged(MouseEvent event) {
        if (shapeEditor != null && shapeEditor.getShape() != null) {
            double dragX = event.getX();
            double dragY = event.getY();
            double offsetX = dragX - pressX;
            double offsetY = dragY - pressY;

            // Aggiorna posizione solo localmente, senza creare un comando
            shapeEditor.dragShape(offsetX, offsetY);
            pressX = dragX;
            pressY = dragY;
        }
    }

    /**
     * Gestisce il rilascio del mouse, ripristinando la visibilità dell'editor.
     */
    @Override
    public void onMouseReleased(MouseEvent event) {

        if (shapeEditor != null && shapeEditor.getShape() != null) {
            double finalX = shapeStartTx + (pressX - shapeEditor.getStartX());
            double finalY = shapeStartTy + (pressY - shapeEditor.getStartY());

            // Crea un comando per l'intero spostamento
            Invoker invoker = Invoker.getInvoker();
            invoker.executeCommand(new DragShape(paper, shapeEditor.getShape(), shapeEditor,shapeStartTx,shapeStartTy,finalX, finalY));
        }

        if (shapeEditor != null) {
            if(paper.getBorderPane() != null){
                if(paneEditor != null){
                  paper.getBorderPane().setRight(paneEditor);  
                }
                else{
                    System.out.println("NULLOLOOLOLO");
                }
            }
            else{
                System.out.println("Bordernull");
            }
            
        } else {

            paper.getBorderPane().setRight(null);
        }
    }

    /**
     * Restituisce l'editor della forma selezionata.
     *
     * @return l'editor della forma.
     */
    public ShapeEditor getEditor() {
        return shapeEditor;
    }

    /**
     * Restituisce il foglio su cui operare.
     *
     * @return il foglio.
     */
    public Paper getPaper() {
        return paper;
    }

    /**
     * Restituisce il pannello in cui l'editor è visibile.
     *
     * @return il pannello editor.
     */
    public AnchorPane getPaneEditor() {
        return paneEditor;
    }

   
    /**
     * Resetta l'editor della forma selezionata.
     */
    public void resetShapeEditor() {
        if (shapeEditor != null && shapeEditor.getShape() != null) {
            shapeEditor.getShape().setEffect(null);
            shapeEditor.setShape(null);
        }
        shapeEditor = null;
   
    }
} 