package controller.application.stock;

import bill.UnitBLL;
import custom.CustomTf;
import dal.Unit;
import database.DBConnection;
import getway.UnitGetway;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class AddUnitController implements Initializable {

    public String unitId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblContent;
    Unit unit = new Unit();
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();
    CustomTf ctf = new CustomTf();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField tfUnitName;
    @FXML
    private Button btnClrUnitName;
    private String usrId;
    private UserNameMedia nameMedia;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btnClose;

    public UserNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(UserNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctf.clearTextFieldByButton(tfUnitName, btnClrUnitName);
        BooleanBinding bb = tfUnitName.textProperty().isEmpty();
        btnSave.disableProperty().bind(bb);


    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

        unit.unitName = tfUnitName.getText().trim();
        unit.unitDescription = taDescription.getText().trim();
        unit.creatorId = usrId;
        unitBLL.save(unit);
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        unit.id = unitId;
        unit.unitName = tfUnitName.getText().trim();
        unit.unitDescription = taDescription.getText().trim();
        unitGetway.update(unit);
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    public void showDetails() {
        unit.id = unitId;
        unitGetway.selectedView(unit);
        tfUnitName.setText(unit.unitName);
        taDescription.setText(unit.unitDescription);
    }


}
