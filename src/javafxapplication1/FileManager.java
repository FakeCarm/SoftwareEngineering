package javafxapplication1;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FileManager {
    
    private final Paper paper;
    
    public FileManager(Paper paper) {
        this.paper = paper;
    }
    
    public static Color fromHexToColor(String hex) {
        try {
            if (hex == null || hex.equals("null")) {
                return Color.TRANSPARENT; // Usa un colore di default per i valori null
            }
            return Color.web(hex);
        } catch (IllegalArgumentException ex) {
            System.err.println("Errore nel parsing del colore: " + hex);
            return Color.TRANSPARENT; // Usa un colore di default per errori
        }
    }
    
    
    public void save(File f) throws IOException {
        if (f == null) {
            return;
        }

        try (PrintWriter saver = new PrintWriter(new FileOutputStream(f))) {
            for (Node n : paper.getAnchorPanePaper().getChildren()) {
                if (n instanceof Line) {
                    // Usa LineTool per salvare i dettagli della linea
                    Line line = (Line) n;
                    LineTool lineTool = new LineTool(paper, null, (Color) line.getStroke(), Color.TRANSPARENT);
                    saver.println("LineTool;" +
                            "startX=" + line.getStartX() + "," +
                            "startY=" + line.getStartY() + "," +
                            "endX=" + line.getEndX() + "," +
                            "endY=" + line.getEndY() + "," +
                            "stroke=" + line.getStroke());
                } else if (n instanceof Rectangle) {
                    // Usa RectangleTool per salvare i dettagli del rettangolo
                    Rectangle rectangle = (Rectangle) n;
                    RectangleTool rectangleTool = new RectangleTool(paper, null, (Color) rectangle.getStroke(), (Color) rectangle.getFill());
                    saver.println("RectangleTool;" +
                        "x=" + rectangle.getX() + "," +
                        "y=" + rectangle.getY() + "," +
                        "width=" + rectangle.getWidth() + "," +
                        "height=" + rectangle.getHeight() + "," +
                        "fill=" + rectangle.getFill() + "," +
                        "stroke=" + rectangle.getStroke());
                } else if (n instanceof Ellipse) {
                    // Usa EllipseTool per salvare i dettagli dell'ellisse
                    Ellipse ellipse = (Ellipse) n;
                    EllipseTool ellipseTool = new EllipseTool(paper, null, (Color) ellipse.getStroke(), (Color) ellipse.getFill());
                    saver.println("EllipseTool;" +
                        "centerX=" + ellipse.getCenterX() + "," +
                        "centerY=" + ellipse.getCenterY() + "," +
                        "radiusX=" + ellipse.getRadiusX() + "," +
                        "radiusY=" + ellipse.getRadiusY() + "," +
                        "fill=" + ellipse.getFill() + "," +
                        "stroke=" + ellipse.getStroke());
                }
            }
            System.out.println("Salvataggio completato.");
        } catch (FileNotFoundException ex) {
            System.err.println("Errore durante il salvataggio: " + ex.getMessage());
        }
    }


    public void load(File f) throws IOException {
        if (f == null) {
            return;
        }

        paper.getAnchorPanePaper().getChildren().clear();

        try (Scanner loader = new Scanner(new FileInputStream(f)).useDelimiter(";").useLocale(Locale.US)) {
            while (loader.hasNext()) {
                try {
                    String line = loader.nextLine();
                    System.out.println("Riga caricata: " + line); // Debug
                    String[] parts = line.split(";");
                    String shapeType = parts[0];

                    // Recupera le proprietà
                    String[] properties = parts[1].split(",");
                    ToolState tool = null;

                    if ("LineTool".equals(shapeType)) {
                        double startX = Double.parseDouble(properties[0].split("=")[1]);
                        double startY = Double.parseDouble(properties[1].split("=")[1]);
                        double endX = Double.parseDouble(properties[2].split("=")[1]);
                        double endY = Double.parseDouble(properties[3].split("=")[1]);
                        Color stroke = Color.web(properties[4].split("=")[1]);

                        // Usa LineTool per ricreare la linea
                        LineTool lineTool = new LineTool(paper, null, stroke, Color.TRANSPARENT);
                        lineTool.currentLine = new Line(startX, startY, endX, endY);
                        lineTool.currentLine.setStroke(stroke);
                        paper.getAnchorPanePaper().getChildren().add(lineTool.currentLine);
                    } else if ("RectangleTool".equals(shapeType)) {
                        double x = Double.parseDouble(properties[0].split("=")[1]);
                        double y = Double.parseDouble(properties[1].split("=")[1]);
                        double width = Double.parseDouble(properties[2].split("=")[1]);
                        double height = Double.parseDouble(properties[3].split("=")[1]);
                        Color fill = Color.web(properties[4].split("=")[1]);
                        Color stroke = Color.web(properties[5].split("=")[1]);

                        // Usa RectangleTool per ricreare il rettangolo
                        RectangleTool rectangleTool = new RectangleTool(paper, null, stroke, fill);
                        rectangleTool.currentRectangle = new Rectangle(x, y, width, height);
                        rectangleTool.currentRectangle.setFill(fill);
                        rectangleTool.currentRectangle.setStroke(stroke);
                        paper.getAnchorPanePaper().getChildren().add(rectangleTool.currentRectangle);
                    } else if ("EllipseTool".equals(shapeType)) {
                        double centerX = Double.parseDouble(properties[0].split("=")[1]);
                        double centerY = Double.parseDouble(properties[1].split("=")[1]);
                        double radiusX = Double.parseDouble(properties[2].split("=")[1]);
                        double radiusY = Double.parseDouble(properties[3].split("=")[1]);
                        Color fill = Color.web(properties[4].split("=")[1]);
                        Color stroke = Color.web(properties[5].split("=")[1]);

                        // Usa EllipseTool per ricreare l'ellisse
                        EllipseTool ellipseTool = new EllipseTool(paper, null, stroke, fill);
                        ellipseTool.currentEllipse = new Ellipse(centerX, centerY, radiusX, radiusY);
                        ellipseTool.currentEllipse.setFill(fill);
                        ellipseTool.currentEllipse.setStroke(stroke);
                        paper.getAnchorPanePaper().getChildren().add(ellipseTool.currentEllipse);
                    }
                } catch (Exception ex) {
                    System.err.println("Errore durante il caricamento di una forma: " + ex.getMessage());
                }
            }
            System.out.println("Caricamento completato.");
        } catch (FileNotFoundException ex) {
            System.err.println("Errore durante il caricamento: " + ex.getMessage());
    }
}





}
