package javafxapplication1;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class RectangleToolTest {

    //private AnchorPane paper;
    private Paper paper;
    private Rectangle testRectangle;
    private RectangleTool rectangleTool;
    private Color testStrokeColor;
    private Color testFillColor;
    private MouseEvent clickOnPaper;

    /**
     * Configura l'ambiente di test, crea un rettangolo di test con colori definiti
     * e inizializza un'istanza di RectangleTool.
     */
    @Before
    public void setUp() throws Exception {
        if (!Platform.isFxApplicationThread()) {
            CountDownLatch latch = new CountDownLatch(1);
            new JFXPanel(); 
            Platform.runLater(latch::countDown);
            latch.await();
        }

        CountDownLatch setupLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            paper = new Paper(new AnchorPane(), new BorderPane());
            testStrokeColor = Color.RED;
            testFillColor = Color.BLACK;
            testRectangle = new Rectangle(0, 0, 10, 20);
            testRectangle.setStroke(testStrokeColor);
            testRectangle.setFill(testFillColor);
            rectangleTool = new RectangleTool(paper, testStrokeColor, testFillColor);
            setupLatch.countDown();
        });
        setupLatch.await(); 

        // Genera un evento di click per il test
        clickOnPaper = new MouseEvent(MouseEvent.MOUSE_PRESSED, 
                testRectangle.getX(), testRectangle.getY(), 
                testRectangle.getX(), testRectangle.getY(), 
                MouseButton.PRIMARY, 1, 
                false, false, false, false, 
                false, false, false, false, 
                false, false, null);
    }

    /**
     * Simula un clic del mouse e verifica se il rettangolo aggiunto alla "carta"
     * ha la stessa posizione e gli stessi attributi di colore del rettangolo di test.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("Test: onMousePressed");
        rectangleTool.onMousePressed(clickOnPaper);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Rectangle);

        Rectangle castedRectangle = (Rectangle) addedShape;
        Assert.assertEquals(testRectangle.getX(), castedRectangle.getX(), 0);
        Assert.assertEquals(testRectangle.getY(), castedRectangle.getY(), 0);
        Assert.assertEquals(testStrokeColor, castedRectangle.getStroke());
        Assert.assertEquals(testFillColor, castedRectangle.getFill());
    }

    /**
     * Simula un clic e un trascinamento del mouse, verifica se il rettangolo
     * ha la stessa larghezza e altezza del rettangolo di test.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("Test: onMouseDragged");
        rectangleTool.onMousePressed(clickOnPaper);

        MouseEvent dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 
                testRectangle.getWidth(), testRectangle.getHeight(), 
                testRectangle.getWidth(), testRectangle.getHeight(), 
                MouseButton.PRIMARY, 1, 
                false, false, false, false, 
                false, false, false, false, 
                false, false, null);

        rectangleTool.onMouseDragged(dragEvent);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Rectangle);

        Rectangle castedRectangle = (Rectangle) addedShape;
        Assert.assertEquals(testRectangle.getWidth(), castedRectangle.getWidth(), 0);
        Assert.assertEquals(testRectangle.getHeight(), castedRectangle.getHeight(), 0);
    }

    /**
     * Simula un clic, un trascinamento e un rilascio del mouse, verifica se il
     * rettangolo finale ha le stesse dimensioni del rettangolo di test.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("Test: onMouseReleased");
    
        // Simula il click per iniziare il rettangolo
        rectangleTool.onMousePressed(clickOnPaper);

        // Simula il trascinamento per impostare larghezza e altezza
        MouseEvent dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED, 
            testRectangle.getWidth(), testRectangle.getHeight(), 
            testRectangle.getWidth(), testRectangle.getHeight(), 
            MouseButton.PRIMARY, 1, 
            false, false, false, false, 
            false, false, false, false, 
            false, false, null);

        rectangleTool.onMouseDragged(dragEvent);

        // Simula il rilascio del mouse
        MouseEvent releaseEvent = new MouseEvent(MouseEvent.MOUSE_RELEASED, 
            testRectangle.getWidth(), testRectangle.getHeight(), 
            testRectangle.getWidth(), testRectangle.getHeight(), 
            MouseButton.PRIMARY, 1, 
            false, false, false, false, 
            false, false, false, false, 
            false, false, null);

        rectangleTool.onMouseReleased(releaseEvent);

        // Verifica il rettangolo creato
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un rettangolo.", addedShape instanceof Rectangle);

        Rectangle castedRectangle = (Rectangle) addedShape;

        // Controlla larghezza e altezza
        Assert.assertEquals("Larghezza non corretta.", testRectangle.getWidth(), castedRectangle.getWidth(), 0);
        Assert.assertEquals("Altezza non corretta.", testRectangle.getHeight(), castedRectangle.getHeight(), 0);
    }

}
