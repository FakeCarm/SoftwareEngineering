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
import javafx.scene.shape.Ellipse;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class EllipseToolTest {

    private Paper paper;
    private Ellipse testEllipse;
    private EllipseTool ellipseTool;
    private Color testStrokeColor;
    private Color testFillColor;
    private MouseEvent clickOnPaper;

   
    
    @Before
    public void setUp() throws Exception {
            new JFXPanel();
            CountDownLatch latch = new CountDownLatch(1);

            Platform.runLater(latch::countDown);
            latch.await();
            paper = new Paper(new AnchorPane(), new BorderPane(),null);
            testStrokeColor = Color.BLUE;
            testFillColor = Color.YELLOW;
            testEllipse = new Ellipse(50, 50, 30, 20);
            testEllipse.setStroke(testStrokeColor);
            testEllipse.setFill(testFillColor);

            ellipseTool = new EllipseTool(paper, testStrokeColor, testFillColor);

            // Genera un evento di click per il test
            clickOnPaper = new MouseEvent(MouseEvent.MOUSE_PRESSED,
                    testEllipse.getCenterX(), testEllipse.getCenterY(),
                    testEllipse.getCenterX(), testEllipse.getCenterY(),
                    MouseButton.PRIMARY, 1,
                    false, false, false, false,
                    false, false, false, false,
                    false, false, null);
           
    }

    /**
     * Simula un clic del mouse e verifica se l'ellisse aggiunta alla "carta"
     * ha la stessa posizione e gli stessi attributi di colore dell'ellisse di test.
     */
    @Test
    public void testOnMousePressed() {
        System.out.println("Test: onMousePressed");
        ellipseTool.onMousePressed(clickOnPaper);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un'ellisse.", addedShape instanceof Ellipse);

        Ellipse castedEllipse = (Ellipse) addedShape;
        Assert.assertEquals(testEllipse.getCenterX(), castedEllipse.getCenterX(), 0);
        Assert.assertEquals(testEllipse.getCenterY(), castedEllipse.getCenterY(), 0);
        Assert.assertEquals(testStrokeColor, castedEllipse.getStroke());
        Assert.assertEquals(testFillColor, castedEllipse.getFill());
    }

    /**
     * Simula un clic e un trascinamento del mouse, verifica se l'ellisse
     * ha gli stessi raggi dell'ellisse di test.
     */
    @Test
    public void testOnMouseDragged() {
        System.out.println("Test: onMouseDragged");
        ellipseTool.onMousePressed(clickOnPaper);

        MouseEvent dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                MouseButton.PRIMARY, 1,
                false, false, false, false,
                false, false, false, false,
                false, false, null);

        ellipseTool.onMouseDragged(dragEvent);
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un'ellisse.", addedShape instanceof Ellipse);

        Ellipse castedEllipse = (Ellipse) addedShape;
        Assert.assertEquals(30, castedEllipse.getRadiusX(), 0);
        Assert.assertEquals(20, castedEllipse.getRadiusY(), 0);
    }

    /**
     * Simula un clic, un trascinamento e un rilascio del mouse, verifica se
     * l'ellisse finale ha i raggi corretti.
     */
    @Test
    public void testOnMouseReleased() {
        System.out.println("Test: onMouseReleased");

        // Simula il click per iniziare l'ellisse
        ellipseTool.onMousePressed(clickOnPaper);

        // Simula il trascinamento per impostare i raggi
        MouseEvent dragEvent = new MouseEvent(MouseEvent.MOUSE_DRAGGED,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                MouseButton.PRIMARY, 1,
                false, false, false, false,
                false, false, false, false,
                false, false, null);

        ellipseTool.onMouseDragged(dragEvent);

        // Simula il rilascio del mouse
        MouseEvent releaseEvent = new MouseEvent(MouseEvent.MOUSE_RELEASED,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                testEllipse.getCenterX() + 60, testEllipse.getCenterY() + 40,
                MouseButton.PRIMARY, 1,
                false, false, false, false,
                false, false, false, false,
                false, false, null);

        ellipseTool.onMouseReleased(releaseEvent);

        // Verifica l'ellisse creata
        Node addedShape = paper.getAnchorPanePaper().getChildren().get(0);
        assertTrue("La forma aggiunta non è un'ellisse.", addedShape instanceof Ellipse);

        Ellipse castedEllipse = (Ellipse) addedShape;

        
        Assert.assertEquals("Raggio X non corretto.", 30, castedEllipse.getRadiusX(), 0);
        Assert.assertEquals("Raggio Y non corretto.", 20, castedEllipse.getRadiusY(), 0);
    }
}
