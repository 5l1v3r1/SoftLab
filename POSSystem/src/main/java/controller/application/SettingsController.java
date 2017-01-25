package controller.application;

import controller.application.settings.MyAccountController;
import controller.application.settings.OrgSettingController;
import database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class SettingsController implements Initializable {
    @FXML
    public BorderPane bpSettings;
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    UserNameMedia usrMedia;
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miOrganize;
    @FXML
    private MenuItem miBackup;
    @FXML
    private StackPane spSettingContent;
    @FXML
    private Label lblCurrentStatus;
    private String userID;

    public UserNameMedia getUsrMedia() {
        return usrMedia;
    }

    public void setUsrMedia(UserNameMedia usrMedia) {
        userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void miMyASccountOnClick(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("My Account");

        MyAccountController myAccount = new MyAccountController();
        UserNameMedia usrIdMedia = new UserNameMedia();

        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/view/application/settings/MyAccount.fxml").openStream());

        usrIdMedia.setId(userID);

        MyAccountController mAccount = fxmlload.getController();

        mAccount.setUsrMediaID(usrMedia);
        mAccount.showDetails();
        AnchorPane acPane = fxmlload.getRoot();

        spSettingContent.getChildren().clear();
        spSettingContent.getChildren().add(acPane);

    }

    @FXML
    private void miOrganizeOnAction(ActionEvent event) throws IOException {
        System.out.println(userID);
        lblCurrentStatus.setText("Org. Setup");

        OrgSettingController orgSetting = new OrgSettingController();
        UserNameMedia useridMedia = new UserNameMedia();

        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/settings/OrgSetting.fxml").openStream());

        useridMedia.setId(userID);

        OrgSettingController orgnizeUsrId = fXMLLoader.getController();
        orgnizeUsrId.setUsrIdMedia(useridMedia);
        orgnizeUsrId.showDetails();
        spSettingContent.getChildren().clear();
        AnchorPane orgAp = fXMLLoader.getRoot();
        spSettingContent.getChildren().add(orgAp);
    }

    @FXML
    private void miBackupOnAction(ActionEvent event) {

    }

    public void settingPermission() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from UserPermission where id=?");
            pst.setString(1, userID);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(18) == 0) {
                    miOrganize.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
