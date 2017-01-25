package controller;

import custom.CustomPf;
import custom.CustomTf;
import dal.Users;
import database.DBConnection;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import media.UserNameMedia;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author HikmatD
 */
public class LoginController implements Initializable {

    Users users = new Users();
    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();
    @FXML
    private TextField tfUserName;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink hlCrateAccount;

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cTF.clearTextFieldByButton(tfUserName, btnUserNameTfClear);
        cPF.clearPassFieldByButton(pfUserPassword, btnPassFieldClear);
        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(pfUserPassword.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);

    }

    @FXML
    private void hlCreateAnAccount(ActionEvent event) throws IOException {
        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("SELECT Id FROM User ORDER BY Id ASC LIMIT 1");
            rs = pst.executeQuery();

            while (rs.next()) {
                apMother.setOpacity(0.5);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eror Dialog");
                alert.setHeaderText("Permission Denied");
                alert.setContentText("This hyperlink only available only for first user.\nYou can not add or create an account by clicking this link.\nYou need to admin permission to create an account");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    apMother.setOpacity(1);
                }
                return;
            }

            loadRegistration();

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnLogin(ActionEvent event) throws IOException {

        DBConnection dbCon = new DBConnection();
        con = dbCon.geConnection();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Application.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);

        if (isValidCondition()) {
            try {
                pst = con.prepareStatement("select * from User where UsrName=? and Password=?");
                pst.setString(1, tfUserName.getText());
                pst.setString(2, pfUserPassword.getText());
                rs = pst.executeQuery();

                while (rs.next()) {
                    pst = con.prepareStatement("select * from User where UsrName=? and Status=?");
                    pst.setString(1, tfUserName.getText());
                    pst.setInt(2, 1);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        UserNameMedia usrNameMedia = new UserNameMedia(rs.getString(1), rs.getString(2));
                        ApplicationController apControl = loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.btnHomeOnClick(event);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.getIcons().add(new Image("/image/icon.png"));
                        adminPanelStage.setTitle(rs.getString(3));
                        adminPanelStage.show();

                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        System.out.println("Now You Ready to go to Admin Panel");
                        return;
                    }
                    System.out.println("Account Not Active");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Confirmation Dialog");
                    alert.setContentText("This account not active right now\n"
                            + "Please contact with admin to active your account \n"
                            + " Thank You");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        return;
                    } else {
                        return;
                    }
                }

                System.out.println("Password Not Match");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Ooops, Error!!!");
                alert.setContentText("User Name and Password did not Match");
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if (tfUserName.getText().trim().isEmpty()
                || pfUserPassword.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Warning Dialog");
            alert.setContentText("Please Fill Text Field And Password Properly!");
            alert.showAndWait();

            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadRegistration() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(true);
            nStage.setTitle("Registration -StoreKeeper");
            nStage.show();
            Stage stage = (Stage) hlCrateAccount.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
