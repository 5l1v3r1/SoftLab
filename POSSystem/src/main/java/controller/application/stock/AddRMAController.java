package controller.application.stock;

import bill.RmaBLL;
import custom.CustomTf;
import dal.RMA;
import database.DBConnection;
import getway.RmaGetway;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import media.UserNameMedia;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class AddRMAController implements Initializable {

    @FXML
    public Label lblContent;
    public String rmaId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    CustomTf ctf = new CustomTf();
    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private UserNameMedia media;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfRMAName;
    @FXML
    private TextField tfRMADayes;
    @FXML
    private TextArea taRMAComment;

    public UserNameMedia getMedia() {
        return media;
    }

    public void setMedia(UserNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding binding = tfRMAName.textProperty().isEmpty()
                .or(tfRMADayes.textProperty().isEmpty());
        btnSave.disableProperty().bind(binding);

        ctf.numaricTextfield(tfRMADayes);
    }

    @FXML
    private void btnSave(ActionEvent event) {
        rma.id = rmaId;
        rma.rmaName = tfRMAName.getText().trim();
        rma.rmaDays = tfRMADayes.getText().trim();
        rma.rmaComment = taRMAComment.getText();
        rma.creatorId = usrId;
        rmaBLL.save(rma);
    }


    @FXML
    private void tfRMADays(KeyEvent event) {

        if (tfRMADayes.getText().matches("[0-9]*")) {

        } else {
            tfRMADayes.clear();
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        rma.id = rmaId;
        rma.rmaName = tfRMAName.getText().trim();
        rma.rmaDays = tfRMADayes.getText().trim();
        rma.rmaComment = taRMAComment.getText();
        rmaBLL.update(rma);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void showDetails() {
        rma.id = rmaId;
        rmaGetway.selectedView(rma);
        tfRMAName.setText(rma.rmaName);
        tfRMADayes.setText(rma.rmaDays);
        taRMAComment.setText(rma.rmaComment);
    }
}

