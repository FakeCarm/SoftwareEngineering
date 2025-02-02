/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author cassd
 */
public class TextToolTest {
    
    private TextTool toolText;
    private Paper paper;
    private Color testStrokeColor = Color.BLUE;
    private Color testFillColor = Color.RED;
    
    public TextToolTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException {
        new JFXPanel(); // Questo avvia il toolkit JavaFX altrimenti viene meno la parte grafica relativa al testo come Font ecc..
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();
        
        AnchorPane anch= new AnchorPane();
        anch.setMaxSize(800, 800);
        BorderPane bord = new BorderPane();
        bord.setMaxSize(1000, 1000);
        this.paper = new Paper(anch, bord, null);
        this.toolText = new TextTool(paper,testStrokeColor,testFillColor);  
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of onMousePressed method, of class TextTool.
     */
    @Test
    public void testOnMousePressed() throws InterruptedException {
        Platform.runLater(() -> {
            MouseEvent pressEvent = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 60, 50, 60, MouseButton.PRIMARY, 1,
                                                   false, false, false, false, true, false, false, false, false, false, null);

            toolText.onMousePressed(pressEvent);

            Text text = toolText.getTextShape();
            assertNotNull("Text should not be null", text);
            assertEquals("Inserisci testo", text.getText());
            assertEquals(50.0, text.getX(), 0.001);
            assertEquals(60.0, text.getY(), 0.001);
        });
        
         Thread.sleep(500);
        
    }

    /**
     * Test of onMouseDragged method, of class TextTool.
     */
    @Test
    public void testOnMouseDragged() {
        
    }

    /**
     * Test of onMouseReleased method, of class TextTool.
     */
    @Test
    public void testOnMouseReleased() {
        
    }

    /**
     * Test of setText method, of class TextTool.
     */
    @Test
    public void testSetText() {
        MouseEvent pressEvent = new MouseEvent(MouseEvent.MOUSE_PRESSED, 50, 60, 50, 60, MouseButton.PRIMARY, 1,
                                                   false, false, false, false, true, false, false, false, false, false, null);

        toolText.onMousePressed(pressEvent);
        String text = "testo";
        this.toolText.setText(text);
        assertEquals(text,toolText.getText());
    }

    /**
     * Test of getText method, of class TextTool.
     */
    @Test
    public void testGetText() {
        String text = "testo";
        this.toolText.setText(text);
        assertNotEquals(this.toolText.getText(),"");
        
    }
    
}
