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

/**
 * Gestisce il salvataggio e il caricamento di forme su file.
 */
public class FileManager {

    
    private final Paper paper;

    /**
     * Costruttore che inizializza il gestore dei file con una determinata area di lavoro.
     *
     * @param paper l'istanza di Paper su cui operare.
     */
    public FileManager(Paper paper) {
        this.paper = paper;
    }

    /**
     * Converte un valore esadecimale di colore in un'istanza di Color di JavaFX.
     *
     * @param hex la rappresentazione esadecimale del colore.
     * @return l'oggetto  Color corrispondente o  Color.TRANSPARENT in caso di errore.
     */
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

    /**
     * Salva le forme presenti nel foglio su un file specificato.
     *
     * @param f il file su cui salvare le informazioni.
     * @throws IOException se si verifica un errore durante l'accesso o la scrittura del file.
     */
    public void save(File f) throws IOException {
        if (f == null) {
            return;
        }

        try (PrintWriter saver = new PrintWriter(new FileOutputStream(f))) {
            for (Node n : paper.getAnchorPanePaper().getChildren()) {
                if (n instanceof Line) {
                    Line line = (Line) n;
                    saver.println("LineTool;" +
                            "startX=" + line.getStartX() + "," +
                            "startY=" + line.getStartY() + "," +
                            "endX=" + line.getEndX() + "," +
                            "endY=" + line.getEndY() + "," +
                            "stroke=" + line.getStroke());
                } else if (n instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) n;
                    saver.println("RectangleTool;" +
                        "x=" + rectangle.getX() + "," +
                        "y=" + rectangle.getY() + "," +
                        "width=" + rectangle.getWidth() + "," +
                        "height=" + rectangle.getHeight() + "," +
                        "fill=" + rectangle.getFill() + "," +
                        "stroke=" + rectangle.getStroke());
                } else if (n instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) n;
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

    /**
     * Carica le forme da un file specificato e le ripristina nel foglio.
     *
     * @param f il file da cui caricare le informazioni.
     * @throws IOException se si verifica un errore durante l'accesso o la lettura del file.
     */
    public void load(File f) throws IOException {
        if (f == null || !f.exists()) {
            throw new IOException("File non trovato: " + (f != null ? f.getAbsolutePath() : "null"));
        }

        paper.getAnchorPanePaper().getChildren().clear();

        try (Scanner loader = new Scanner(new FileInputStream(f)).useDelimiter(";").useLocale(Locale.US)) {
            while (loader.hasNext()) {
                try {
                    String line = loader.nextLine();
                    System.out.println("Riga caricata: " + line);
                    String[] parts = line.split(";");
                    String shapeType = parts[0];

                    String[] properties = parts[1].split(",");

                    if ("LineTool".equals(shapeType)) {
                        double startX = Double.parseDouble(properties[0].split("=")[1]);
                        double startY = Double.parseDouble(properties[1].split("=")[1]);
                        double endX = Double.parseDouble(properties[2].split("=")[1]);
                        double endY = Double.parseDouble(properties[3].split("=")[1]);
                        Color stroke = Color.web(properties[4].split("=")[1]);

                        Line lineShape = new Line(startX, startY, endX, endY);
                        lineShape.setStroke(stroke);
                        lineShape.setStrokeWidth(5);
                        paper.getAnchorPanePaper().getChildren().add(lineShape);
                    } else if ("RectangleTool".equals(shapeType)) {
                        double x = Double.parseDouble(properties[0].split("=")[1]);
                        double y = Double.parseDouble(properties[1].split("=")[1]);
                        double width = Double.parseDouble(properties[2].split("=")[1]);
                        double height = Double.parseDouble(properties[3].split("=")[1]);
                        Color fill = Color.web(properties[4].split("=")[1]);
                        Color stroke = Color.web(properties[5].split("=")[1]);

                        Rectangle rectangleShape = new Rectangle(x, y, width, height);
                        rectangleShape.setFill(fill);
                        rectangleShape.setStroke(stroke);
                        rectangleShape.setStrokeWidth(5);
                        paper.getAnchorPanePaper().getChildren().add(rectangleShape);
                    } else if ("EllipseTool".equals(shapeType)) {
                        double centerX = Double.parseDouble(properties[0].split("=")[1]);
                        double centerY = Double.parseDouble(properties[1].split("=")[1]);
                        double radiusX = Double.parseDouble(properties[2].split("=")[1]);
                        double radiusY = Double.parseDouble(properties[3].split("=")[1]);
                        Color fill = Color.web(properties[4].split("=")[1]);
                        Color stroke = Color.web(properties[5].split("=")[1]);

                        Ellipse ellipseShape = new Ellipse(centerX, centerY, radiusX, radiusY);
                        ellipseShape.setFill(fill);
                        ellipseShape.setStroke(stroke);
                        ellipseShape.setStrokeWidth(5);
                        paper.getAnchorPanePaper().getChildren().add(ellipseShape);
                    }
                } catch (Exception ex) {
                    System.err.println("Errore durante il caricamento di una forma: " + ex.getMessage());
                }
            }
            System.out.println("Caricamento completato.");
        } catch (FileNotFoundException ex) {
            throw new IOException("Errore durante il caricamento: File non trovato", ex);
        }
    }
}
