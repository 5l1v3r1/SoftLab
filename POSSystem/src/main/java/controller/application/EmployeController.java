package controller.application;

import controller.application.employe.AddEmployeController;
import controller.application.employe.ViewEmployeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class EmployeController implements Initializable {
    @FXML
    public BorderPane bpContent;
    Image image = new Image("/icon/d.png");
    @FXML
    private MenuItem btnViewEmployee;
    @FXML
    private MenuItem btnAddEmployee;
    private String userId;
    private UserNameMedia nameMedia;
    @FXML
    private StackPane spEmployeContent;
    @FXML
    private Label lblView;
    @FXML
    private ImageView ivEmployeIcon;

    public UserNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(UserNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivEmployeIcon.setImage(image);
    }

    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        lblView.setText("Employee");
        ViewEmployeController vec = new ViewEmployeController();
        UserNameMedia media = new UserNameMedia();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/employe/ViewEmploye.fxml").openStream());
        media.setId(userId);

        ViewEmployeController viewEmployeController = fXMLLoader.getController();
        viewEmployeController.setNameMedia(nameMedia);
        viewEmployeController.showDetails();
        viewEmployeController.btnClrCreatortf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrEmailtf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrFulNametf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrSalarytf.getStylesheets().add("/style/btnOnText.css");
        viewEmployeController.btnClrPhonetf.getStylesheets().add("/style/btnOnText.css");
//        viewEmployeController.checqPermission();

        AnchorPane acPane = fXMLLoader.getRoot();

        spEmployeContent.getChildren().clear();

        spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        lblView.setText("Add Employee");
        AddEmployeController vec = new AddEmployeController();
        UserNameMedia media = new UserNameMedia();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/employe/AddEmploye.fxml").openStream());
        media.setId(userId);

        AddEmployeController addEmployeController = fXMLLoader.getController();
        addEmployeController.setNameMedia(nameMedia);
        addEmployeController.btnClrEmail.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrFullName.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrPassword.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrPhone.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrSalary.getStylesheets().add("/style/btnOnText.css");
        addEmployeController.btnClrUsrName.getStylesheets().add("/style/btnOnText.css");

        AnchorPane acPane = fXMLLoader.getRoot();

        spEmployeContent.getChildren().clear();

        spEmployeContent.getChildren().add(acPane);

    }


}
