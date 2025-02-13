package javafxapplication1;

import Command.AddShape;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class FileManagerTest {

    private FileManager fileManager;
    private Paper paper;
    private Path tempFile;

    @BeforeClass
    public static void setUpClass() throws Exception {
        new JFXPanel(); 
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await(); 
    }

    @Before
    public void setUp() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            paper = new Paper(new AnchorPane(), new BorderPane(),null);
            fileManager = new FileManager(paper);
            try {
                tempFile = Files.createTempFile("", "");
            } catch (IOException ex) {
                Logger.getLogger(FileManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            latch.countDown();
        });
        latch.await(2, TimeUnit.SECONDS);
    }
    
    @Test
    public void testSaveAndLoad() throws Exception {
        System.out.println("saveAndLoad");

        // Aggiungi figure al Paper
        Line line = new Line(10, 10, 100, 100);
        line.setStroke(Color.BLUE);
        paper.addOnPaper(line);

        Rectangle rectangle = new Rectangle(50, 50, 200, 100);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.RED);
        paper.addOnPaper(rectangle);

        Ellipse ellipse = new Ellipse(150, 150, 75, 50);
        ellipse.setFill(Color.YELLOW);
        ellipse.setStroke(Color.BLACK);
        paper.addOnPaper(ellipse);

        // Salva le figure nel file temporaneo
        fileManager.save(tempFile.toFile());

        // Rimuovi tutte le figure dal Paper
        paper.getAnchorPanePaper().getChildren().clear();
        assertEquals("Il Paper dovrebbe essere vuoto dopo la cancellazione", 0, paper.getAnchorPanePaper().getChildren().size());

        // Carica le figure dal file temporaneo
        fileManager.load(tempFile.toFile());

        // Verifica che le figure siano state caricate correttamente
        assertEquals("Il Paper dovrebbe contenere 3 figure", 3, paper.getAnchorPanePaper().getChildren().size());

        Line loadedLine = (Line) paper.getAnchorPanePaper().getChildren().get(0);
        assertEquals("La linea caricata dovrebbe avere il colore BLUE", Color.BLUE, loadedLine.getStroke());
        assertEquals("La linea caricata dovrebbe avere le coordinate corrette", 10, loadedLine.getStartX(), 0.01);

        Rectangle loadedRectangle = (Rectangle) paper.getAnchorPanePaper().getChildren().get(1);
        assertEquals("Il rettangolo caricato dovrebbe avere il colore di riempimento GREEN", Color.GREEN, loadedRectangle.getFill());
        assertEquals("Il rettangolo caricato dovrebbe avere la larghezza corretta", 200, loadedRectangle.getWidth(), 0.01);

        Ellipse loadedEllipse = (Ellipse) paper.getAnchorPanePaper().getChildren().get(2);
        assertEquals("L'ellisse caricata dovrebbe avere il colore di riempimento YELLOW", Color.YELLOW, loadedEllipse.getFill());
        assertEquals("L'ellisse caricata dovrebbe avere il raggio X corretto", 75, loadedEllipse.getRadiusX(), 0.01);
    }

    @Test(expected = IOException.class)
    public void testLoadWithNonExistentFile() throws Exception {
        System.out.println("loadWithNonExistentFile");

        // Prova a caricare un file inesistente
        File nonExistentFile = new File("nonexistentfile.dnp");
        fileManager.load(nonExistentFile);
    }
}
