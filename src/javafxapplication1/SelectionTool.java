/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/**
 *
 * @author cassd
 */
public class SelectionTool extends ToolState {
    private Paper paper;
    private ShapeEditor shapeEditor;
    private Shape selectedShape; // Figura selezionata

    public SelectionTool(Paper paper) {
        this.paper = paper;
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        double startX = event.getX();
        double startY = event.getY();

        ObservableList<Node> shapes = paper.getAnchorPanePaper().getChildren();
        System.out.println("OGGETTI SUL FOGLIO: " + shapes.size());

        for (Node node : shapes) {
            if (node instanceof Shape && node.contains(startX, startY)) {
                Shape clickedShape = (Shape) node;

                // Controlla se la figura cliccata è già selezionata
                if (clickedShape == selectedShape) {
                    // Deseleziona la figura attuale
                    selectedShape.setEffect(null); // Rimuove l'effetto visivo
                    selectedShape = null; // Rimuove il riferimento
                    shapeEditor = null; // Rimuove l'editor associato
                    System.out.println("Figura deselezionata.");
                    return;
                }

                // Seleziona una nuova figura
                if (selectedShape != null) {
                    selectedShape.setEffect(null); // Rimuove l'effetto dalla figura precedentemente selezionata
                }

                selectedShape = clickedShape; // Imposta la nuova figura selezionata
                System.out.println("FIGURA SELEZIONATA: " + selectedShape.getId());

                // Crea il relativo ShapeEditor
                if (selectedShape instanceof Ellipse) {
                    this.shapeEditor = new EllipseShapeEditor(selectedShape, paper, startX, startY);
                } else if (selectedShape instanceof Rectangle) {
                    this.shapeEditor = new RectangleShapeEditor(selectedShape, paper, startX, startY);
                } else if (selectedShape instanceof Line) {
                    this.shapeEditor = new LineShapeEditor(selectedShape, paper, startX, startY);
                }

                // Applica un effetto visivo per indicare la selezione
                DropShadow dropShadow = new DropShadow();
                dropShadow.setRadius(5.0);
                dropShadow.setOffsetX(3.0);
                dropShadow.setOffsetY(3.0);
                dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
                selectedShape.setEffect(dropShadow);

                return; // Interrompi il ciclo una volta trovata la figura
            }
        }

        // Se clicchi in un'area vuota, deseleziona la figura selezionata (se esiste)
        if (selectedShape != null) {
            selectedShape.setEffect(null);
            selectedShape = null;
            shapeEditor = null;
            System.out.println("Figura deselezionata cliccando su un'area vuota.");
        }
    }


    @Override
    public void onMouseDragged(MouseEvent event) {
        if (this.shapeEditor != null) {
            double dragX = event.getX();
            double dragY = event.getY();
            double offsetX = dragX - shapeEditor.getStartX();
            double offsetY = dragY - shapeEditor.getStartY();

            Invoker invoker = Invoker.getInvoker();
            if (invoker != null) {
                invoker.executeCommand(new DragShape(paper, this.shapeEditor.getShape(), this.shapeEditor, offsetX, offsetY));
                this.shapeEditor.setStartX(dragX);
                this.shapeEditor.setStartY(dragY);
            }
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        // Non rimuovere l'effetto visivo: rimane fino alla rimozione o nuova selezione
    }

    public Shape getSelectedShape() {
        return this.selectedShape; // Ritorna la figura selezionata
    }
}

