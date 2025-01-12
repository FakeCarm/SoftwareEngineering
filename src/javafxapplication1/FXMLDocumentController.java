package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorPanePaper;

    @FXML
    private ColorPicker colorPickerLine;

    private LineTool lineTool;
    
    private boolean lineMode = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lineTool = new LineTool(anchorPanePaper);

        
        anchorPanePaper.setOnMousePressed(lineTool::onMousePressed);
        anchorPanePaper.setOnMouseDragged(lineTool::onMouseDragged);
        anchorPanePaper.setOnMouseReleased(lineTool::onMouseReleased);

        
        colorPickerLine.setOnAction(event -> {
            lineTool.setLineColor(colorPickerLine.getValue());
        });
    }

    @FXML
    private void handleLineButtonAction() {
        lineMode = true;
        System.out.println("Strumento Linea attivato.");
    }
    
    @FXML
    private void onMousePressedPaper(MouseEvent event) {
        lineTool.onMousePressed(event);
    }

    @FXML
    private void onMouseDraggedPaper(MouseEvent event) {
        lineTool.onMouseDragged(event);
    }

    @FXML
    private void onMouseReleasedPaper(MouseEvent event) {
        lineTool.onMouseReleased(event);
    }

}
