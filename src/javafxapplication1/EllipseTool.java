package javafxapplication1;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class EllipseTool extends SelectedShapeTool {

    Ellipse currentEllipse;
    private Paper anchorPanePaper;
    public int count = 0; // Per assegnare un ID univoco a ogni ellisse
    private double startX;
    private double startY;

    public EllipseTool(Paper anchorPanePaper, AnchorPane anchorPaneBar, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        super.anchorPaneBar = anchorPaneBar;
        this.anchorPanePaper = anchorPanePaper;
    }

    @Override
    public void onMousePressed(MouseEvent event) {
        currentEllipse = new Ellipse();
        currentEllipse.setId("ellipse" + (count++));

        // Imposta il punto iniziale dell'ellisse
        this.startX = event.getX();
        this.startY = event.getY();
        currentEllipse.setCenterX(this.startX);
        currentEllipse.setCenterY(this.startY);
        currentEllipse.setRadiusX(0);
        currentEllipse.setRadiusY(0);

        // Colori aggiornati automaticamente
        currentEllipse.setStroke(strokeColor.get());
        currentEllipse.setFill(fillColor.get());
        currentEllipse.setStrokeWidth(5);

        // Aggiunge l'ellisse al Paper tramite l'Invoker
        Invoker invoker = Invoker.getInvoker();
        if (invoker != null) {
            invoker.executeCommand(new AddShape(this.anchorPanePaper, currentEllipse));
        }
    }

    @Override
    public void onMouseDragged(MouseEvent event) {
        if (currentEllipse != null) {
            double currentX = event.getX();
            double currentY = event.getY();

            // Calcola i raggi in base alla distanza dal punto iniziale
            double radiusX = Math.abs(this.startX - currentX) / 2;
            double radiusY = Math.abs(this.startY - currentY) / 2;

            // Imposta il centro e i raggi
            currentEllipse.setRadiusX(radiusX);
            currentEllipse.setRadiusY(radiusY);
            currentEllipse.setCenterX((this.startX + currentX) / 2);
            currentEllipse.setCenterY((this.startY + currentY) / 2);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent event) {
        if (currentEllipse != null) {
            double finalX = event.getX();
            double finalY = event.getY();

            // Aggiorna i raggi e il centro una volta terminato il disegno
            double radiusX = Math.abs(this.startX - finalX) / 2;
            double radiusY = Math.abs(this.startY - finalY) / 2;
            currentEllipse.setRadiusX(radiusX);
            currentEllipse.setRadiusY(radiusY);
            currentEllipse.setCenterX((this.startX + finalX) / 2);
            currentEllipse.setCenterY((this.startY + finalY) / 2);

            // Resetta l'ellisse corrente
            currentEllipse = null;
        }
    }
}
