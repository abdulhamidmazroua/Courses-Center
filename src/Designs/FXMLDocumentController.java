//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Designs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Button btn;
    @FXML
    private HBox root;

    public FXMLDocumentController() {
    }

    @FXML
    void hove(MouseEvent event) {
        this.btn.setText("ahmed");
    }

    public void initialize(URL url, ResourceBundle rb) {
    }
}
