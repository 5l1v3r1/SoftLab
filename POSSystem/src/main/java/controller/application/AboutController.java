package controller.application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class AboutController implements Initializable {
    Image image = new Image("/image/myPic2.jpg");
    @FXML
    private ImageView imgMyImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgMyImg.setImage(image);
    }

}
